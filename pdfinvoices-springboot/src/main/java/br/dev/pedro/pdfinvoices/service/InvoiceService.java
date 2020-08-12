package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String cdnURL;
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(UserService userService, InvoiceRepository invoiceRepository, @Value("${cdn.url}") String cdnURL) {
        this.userService = userService;
        this.invoiceRepository = invoiceRepository;
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

    @Transactional
    public Iterable<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }

    @Transactional
    public Iterable<Invoice> findByUserId(String userId) {
        return this.invoiceRepository.findByUserId(userId);
    }

    @Transactional
    public Invoice create(String userID, Integer amount) {
        String generatePdfURL = this.cdnURL + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setPdfURL(generatePdfURL);
        invoice.setAmount(amount);
        invoice.setUserID(userID);

        return this.invoiceRepository.save(invoice);
    }
}
