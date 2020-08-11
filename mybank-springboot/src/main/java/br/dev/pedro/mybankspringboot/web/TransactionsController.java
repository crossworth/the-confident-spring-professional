package br.dev.pedro.mybankspringboot.web;

import br.dev.pedro.mybankspringboot.dto.TransactionDTO;
import br.dev.pedro.mybankspringboot.model.Transaction;
import br.dev.pedro.mybankspringboot.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionsController {
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return this.transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        return this.transactionService.create(transactionDTO.getAmount(), transactionDTO.getTimestamp(), transactionDTO.getReference());
    }
}
