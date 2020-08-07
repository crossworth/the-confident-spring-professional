package br.dev.pedro.mybank.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Profile("dev")
public class TransactionLoader {
    private final TransactionService transactionService;

    public TransactionLoader(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Seeding transactions");
        this.transactionService.create(10000, LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC), "Food");
        this.transactionService.create(25000, LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC), "More food");
    }
}
