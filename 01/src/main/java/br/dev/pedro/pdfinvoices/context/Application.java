package br.dev.pedro.pdfinvoices.context;

import br.dev.pedro.pdfinvoices.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {
    public static final InvoiceService invoiceService = new InvoiceService();
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
