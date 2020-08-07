package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.context.Application;
import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {
    private final UserService userService;

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll() {
        return this.invoices;
    }

    public Invoice create(String userID, Integer amount) {
        User user = this.userService.findByID(userID);
        if (user == null) {
            throw new IllegalStateException();
        }

        // TODO(Pedro): create the real PDF
        Invoice invoice =  new Invoice(userID, amount, "http://www.africau.edu/images/default/sample.pdf");
        this.invoices.add(invoice);
        return invoice;
    }


}
