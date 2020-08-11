package br.dev.pedro.pdfinvoices.web;

import br.dev.pedro.pdfinvoices.dto.InvoiceDTO;
import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return this.invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        return this.invoiceService.create(invoiceDTO.getUserID(), invoiceDTO.getAmount());
    }
}
