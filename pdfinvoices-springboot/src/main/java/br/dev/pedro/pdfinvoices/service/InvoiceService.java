package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String cdnURL;
    private final JdbcTemplate jdbcTemplate;

    public InvoiceService(UserService userService, JdbcTemplate jdbcTemplate, @Value("${cdn.url}") String cdnURL) {
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
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
    public List<Invoice> findAll() {
        return this.jdbcTemplate.query("SELECT id, user_id, pdf_url, amount from invoices", (resultSet, i) -> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfURL(resultSet.getString("pdf_url"));
            invoice.setUserID(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    @Transactional
    public Invoice create(String userID, Integer amount) {
        String generatePdfURL = this.cdnURL + "/images/default/sample.pdf";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO invoices (user_id, pdf_url, amount) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, userID);
            ps.setString(2, generatePdfURL);
            ps.setInt(3, amount);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? keyHolder.getKeys().values().iterator().next().toString() : null;

        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setPdfURL(generatePdfURL);
        invoice.setAmount(amount);
        invoice.setUserID(userID);

        return invoice;
    }
}
