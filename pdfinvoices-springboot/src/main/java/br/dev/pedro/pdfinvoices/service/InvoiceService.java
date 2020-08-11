package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();
    private final UserService userService;
    private final String cdnURL;

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnURL) {
        this.userService = userService;
        this.cdnURL = cdnURL;
    }

    @PostConstruct
    public void init() {
        System.out.println("Setup something before anything...");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Teardown something after everything...");
    }

    public List<Invoice> findAll() {
        return this.invoices;
    }

    public Invoice create(String userID, Integer amount) {
        User user = this.userService.findByID(userID);

        if (user == null) {
            throw new IllegalStateException();
        }

        String generatePdfURL = this.cdnURL + "/images/default/sample.pdf";

        Invoice invoice = new Invoice(userID, amount, generatePdfURL);
        this.invoices.add(invoice);
        return invoice;
    }


}
