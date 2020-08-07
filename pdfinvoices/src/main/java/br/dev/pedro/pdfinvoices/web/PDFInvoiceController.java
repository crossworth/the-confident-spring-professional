package br.dev.pedro.pdfinvoices.web;

import br.dev.pedro.pdfinvoices.dto.InvoiceDTO;
import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller tells spring that this class handle web related responses json, xml, html
 * ResponseBody tells spring that what we return from the methods should be write directly to
 * the output stream from the servlet, but without a html template framework (which is the default)
 */

@RestController // this is a rest controller (a Controller with ResponseBody)
public class PDFInvoiceController {
    private final InvoiceService invoiceService;

    public PDFInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
//    @RequestMapping(value = "/invoices", method = RequestMethod.GET) // same as GetMapping
    public List<Invoice> invoices() {
        return this.invoiceService.findAll();
    }

    /**
     * RequestParam tell spring that we **need* a param with the names provides (var name if no name is provided)
     * It will try convert the param to the correct type as well
     */
    @PostMapping("/invoices/query_string")
    public Invoice createInvoiceWithQueryString(@RequestParam("user_id") String userID, @RequestParam Integer amount) {
        return this.invoiceService.create(userID, amount);
    }

    @PostMapping("/invoices/path_variables/{userID}/{amount}")
    public Invoice createInvoiceWithPathVariable(@PathVariable String userID, @PathVariable Integer amount) {
        return this.invoiceService.create(userID, amount);
    }

    // Request body (json as body)
    @PostMapping("/invoices")
    public Invoice createInvoiceWithRequestBody(@RequestBody @Valid InvoiceDTO invoiceDTO) {
        return this.invoiceService.create(invoiceDTO.getUserID(), invoiceDTO.getAmount());
    }
}
