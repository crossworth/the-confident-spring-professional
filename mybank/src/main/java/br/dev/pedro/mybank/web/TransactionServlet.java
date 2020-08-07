package br.dev.pedro.mybank.web;

import br.dev.pedro.mybank.context.ApplicationConfiguration;
import br.dev.pedro.mybank.model.Transaction;
import br.dev.pedro.mybank.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionServlet extends HttpServlet {
    private TransactionService transactionService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        this.transactionService = ctx.getBean(TransactionService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");

        if (!req.getRequestURI().equalsIgnoreCase("/transactions")) {
            resp.getWriter().print("{error: true, message: 'use /transactions route'}");
            return;
        }

        List<Transaction> transactions = this.transactionService.findAll();
        resp.getWriter().print(this.objectMapper.writeValueAsString(transactions));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");

        if (!req.getRequestURI().equalsIgnoreCase("/transactions")) {
            resp.getWriter().print("{error: true, message: 'use /transactions route'}");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // we only allow integers, so $ 10.50 should be sent as 1050
        Integer amount = Integer.valueOf(req.getParameter("amount"));

        // date should be something like: 2020-05-03 13:20:10
        LocalDateTime timestamp = LocalDateTime.parse(req.getParameter("timestamp"), formatter);
        String reference = req.getParameter("reference");

        Transaction transaction = this.transactionService.create(amount, timestamp, reference);
        resp.getWriter().print(this.objectMapper.writeValueAsString(transaction));
    }
}

