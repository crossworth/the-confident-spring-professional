package br.dev.pedro.pdfinvoices.web;

import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller tells spring that this class handle web related responses json, xml, html
 * ResponseBody tells spring that what we return from the methods should be write directly to
 * the output stream from the servlet, but without a html template framework (which is the default)
 */

@RestController // this is a rest controller (a Controller with ResponseBody)
public class PDFInvoiceController {
    private final InvoiceService invoiceService;

    public PDFInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
//    @RequestMapping(value = "/invoices", method = RequestMethod.GET) // same as GetMapping
    public List<Invoice> invoices() {
        return this.invoiceService.findAll();
    }
}
