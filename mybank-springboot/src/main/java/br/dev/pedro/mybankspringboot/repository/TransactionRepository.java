package br.dev.pedro.mybankspringboot.repository;

import br.dev.pedro.mybankspringboot.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
