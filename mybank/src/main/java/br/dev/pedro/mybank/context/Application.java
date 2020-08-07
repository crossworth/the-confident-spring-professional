package br.dev.pedro.mybank.context;

import br.dev.pedro.mybank.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class Application {
    public static final TransactionService transactionService = new TransactionService();
    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        Application.objectMapper.registerModule(new JSR310Module());
        Application.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
