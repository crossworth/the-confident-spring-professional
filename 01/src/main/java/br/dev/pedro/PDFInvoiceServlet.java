package br.dev.pedro;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PDFInvoiceServlet extends HttpServlet {
    private InvoiceService invoiceService = new InvoiceService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getRequestURI().equalsIgnoreCase("/")) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello world!</h1>\n" +
                            "<p>This is my very first, embedded Tomcat, HTML page!</p>\n" +
                            "</body>\n" +
                            "</html>"
            );
        } else if (req.getRequestURI().equalsIgnoreCase("/invoices")) {
            resp.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = this.invoiceService.findAll();
            resp.getWriter().print(this.objectMapper.writeValueAsString(invoices));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getRequestURI().equalsIgnoreCase("/invoices")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // getParameter returns the query string
        String userID = req.getParameter("user_id");
        Integer amount = Integer.valueOf(req.getParameter("amount"));

        Invoice invoice = this.invoiceService.create(userID, amount);
        resp.setContentType("application/json; charset=UTF-8");
        String json = this.objectMapper.writeValueAsString(invoice);
        resp.getWriter().print(json);
    }
}
