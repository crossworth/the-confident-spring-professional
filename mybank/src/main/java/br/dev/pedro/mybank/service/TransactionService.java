package br.dev.pedro.mybank.service;

import br.dev.pedro.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private String slogan;

    public TransactionService(@Value("${bank.slogan}") String slogan) {
        this.slogan = slogan;
    }

    public List<Transaction> findAll() {
        return this.transactions;
    }

    public Transaction create(Integer amount, LocalDateTime timestamp, String reference) {
        Transaction transaction = new Transaction(amount, timestamp, reference);
        transaction.setSlogan(this.slogan);
        this.transactions.add(transaction);
        return transaction;
    }
}
