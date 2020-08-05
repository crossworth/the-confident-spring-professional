package br.dev.pedro.pdfinvoices.context;

import br.dev.pedro.pdfinvoices.service.InvoiceService;
import br.dev.pedro.pdfinvoices.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(Application.userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
