package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     * Another way of using is field injection
     */

    @Autowired
    private UserService userService = null; // when using Autowired cannot be final

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return this.invoices;
    }

    public Invoice create(String userID, Integer amount) {
        User user = this.userService.findByID(userID);
        if (user == null) {
            throw new IllegalStateException();
        }

        // TODO(Pedro): create the real PDF
        Invoice invoice = new Invoice(userID, amount, "http://www.africau.edu/images/default/sample.pdf");
        this.invoices.add(invoice);
        return invoice;
    }


}
