package br.dev.pedro.pdfinvoices.web;

import br.dev.pedro.pdfinvoices.dto.InvoiceDTO;
import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public Iterable<Invoice> invoices() {
        return this.invoiceService.findAll();
    }

    @GetMapping("/invoices/user/{userId}")
    public Iterable<Invoice> invoicesByUserId(@PathVariable String userId) {
        return this.invoiceService.findByUserId(userId);
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        return this.invoiceService.create(invoiceDTO.getUserID(), invoiceDTO.getAmount());
    }
}
