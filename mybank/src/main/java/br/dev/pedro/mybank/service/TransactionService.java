package br.dev.pedro.mybank.service;

import br.dev.pedro.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class TransactionService {
    private final String slogan;
    private final JdbcTemplate jdbcTemplate;

    public TransactionService(@Value("${bank.slogan}") String slogan, JdbcTemplate jdbcTemplate) {
        this.slogan = slogan;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> findAll() {
        return this.jdbcTemplate.query("SELECT id, amount, timestamp, reference, slogan FROM transactions", (resultSet, i) -> {
            Transaction transaction = new Transaction();
            transaction.setId(resultSet.getObject("id").toString());
            transaction.setAmount(resultSet.getInt("amount"));
            transaction.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
            transaction.setReference(resultSet.getString("reference"));
            transaction.setSlogan(resultSet.getString("slogan"));
            return transaction;
        });
    }

    public Transaction create(Integer amount, LocalDateTime timestamp, String reference) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO transactions (amount, timestamp, reference, slogan) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, amount);
            ps.setTimestamp(2, Timestamp.from(timestamp.toInstant(ZoneOffset.UTC)));
            ps.setString(3, reference);
            ps.setString(4, slogan);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? keyHolder.getKeys().values().iterator().next().toString() : null;

        Transaction transaction = new Transaction();
        transaction.setId(uuid);
        transaction.setAmount(amount);
        transaction.setReference(reference);
        transaction.setTimestamp(timestamp);
        transaction.setSlogan(this.slogan);
        return transaction;
    }
}
