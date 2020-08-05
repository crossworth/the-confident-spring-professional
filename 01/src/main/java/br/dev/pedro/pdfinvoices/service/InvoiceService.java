package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.Invoice;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {
    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userID, Integer amount) {
        // TODO(Pedro): create the real PDF
        Invoice invoice =  new Invoice(userID, amount, "http://www.africau.edu/images/default/sample.pdf");
        this.invoices.add(invoice);
        return invoice;
    }


}
