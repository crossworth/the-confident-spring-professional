package br.dev.pedro.mybankspringboot.service;

import br.dev.pedro.mybankspringboot.model.Transaction;
import br.dev.pedro.mybankspringboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class TransactionService {
    private final String slogan;
    private final TransactionRepository transactionRepository;

    public TransactionService(@Value("${bank.slogan}") String slogan, TransactionRepository transactionRepository) {
        this.slogan = slogan;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Iterable<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    @Transactional
    public Transaction create(Integer amount, LocalDateTime timestamp, String reference) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReference(reference);
        transaction.setTimestamp(timestamp);
        transaction.setSlogan(this.slogan);
        return this.transactionRepository.save(transaction);
    }
}
