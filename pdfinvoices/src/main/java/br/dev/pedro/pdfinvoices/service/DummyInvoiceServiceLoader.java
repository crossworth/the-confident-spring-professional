package br.dev.pedro.pdfinvoices.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service // same as Component but more explicit
@Profile("dev") // only when on dev profile
public class DummyInvoiceServiceLoader {
    private final InvoiceService invoiceService;

    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Creating dev invoices...");
        this.invoiceService.create("1", 1000);
        this.invoiceService.create("1", 5000);
        this.invoiceService.create("3", 45000);
    }
}
