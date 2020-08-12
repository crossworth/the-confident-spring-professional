package br.dev.pedro.pdfinvoices.repository;

import br.dev.pedro.pdfinvoices.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {

}
