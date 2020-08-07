package br.dev.pedro.mybank.service;

import br.dev.pedro.mybank.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public List<Transaction> findAll() {
        return this.transactions;
    }

    public Transaction create(Integer amount, LocalDateTime timestamp, String reference) {
        Transaction transaction = new Transaction(amount, timestamp, reference);
        this.transactions.add(transaction);
        return transaction;
    }
}
