package br.dev.pedro.pdfinvoices.web;

import br.dev.pedro.pdfinvoices.dto.InvoiceDTO;
import br.dev.pedro.pdfinvoices.model.Invoice;
import br.dev.pedro.pdfinvoices.service.InvoiceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Controller tells spring that this class handle web related responses json, xml, html
 * ResponseBody tells spring that what we return from the methods should be write directly to
 * the output stream from the servlet, but without a html template framework (which is the default)
 */

@RestController // this is a rest controller (a Controller with ResponseBody)
@Validated // tell spring that the methods should be validated
public class InvoicesController {
    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
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
    public Invoice createInvoiceWithRequestParam(@RequestParam("user_id") @NotBlank String userID, @RequestParam @Min(10) @Max(50) Integer amount) {
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
