package br.dev.pedro.pdfinvoices.context;

import br.dev.pedro.pdfinvoices.service.InvoiceService;
import br.dev.pedro.pdfinvoices.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PDFInvoiceApplicationCofiguration {

    /**
     * Bean
     * In Spring, the objects that form the backbone of your
     * application and that are managed by the Spring IoC container
     * are called beans. A bean is an object that is instantiated,
     * assembled, and otherwise managed by a Spring IoC container.
     * <p>
     * By default Beans are singletons, to change to a prototype model
     * Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
     */

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public InvoiceService invoiceService(UserService userService) {
        return new InvoiceService((userService));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
