package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.context.Application;
import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {
    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userID, Integer amount) {
        User user = Application.userService.findByID(userID);
        if (user == null) {
            throw new IllegalStateException();
        }

        // TODO(Pedro): create the real PDF
        Invoice invoice =  new Invoice(userID, amount, "http://www.africau.edu/images/default/sample.pdf");
        this.invoices.add(invoice);
        return invoice;
    }


}
