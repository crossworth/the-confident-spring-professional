package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    /**
     * Older versions of spring needed Autowired annotation on the constructor
     * to let the the Spring IoC know that the parameters was dependencies
     * We have to use Autowired only when using multiple constructors
     * to let spring knows which one to use
     * <p>
     * Another way of using is field injection or setter injection
     */
    private final UserService userService;
    private final String cdnURL;

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnURL) {
        this.userService = userService;
        this.cdnURL = cdnURL;
    }

    @PostConstruct
    public void init() {
        System.out.println("Setup something before anything...");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Teardown something after everything...");
    }

    public List<Invoice> findAll() {
        return this.invoices;
    }

    public Invoice create(String userID, Integer amount) {
        User user = this.userService.findByID(userID);
        if (user == null) {
            throw new IllegalStateException();
        }

        // TODO(Pedro): create the real PDF
        Invoice invoice = new Invoice(userID, amount, this.cdnURL + "/images/default/sample.pdf");
        this.invoices.add(invoice);
        return invoice;
    }


}
