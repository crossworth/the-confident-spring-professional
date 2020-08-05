package br.dev.pedro;

public class InvoiceService {
    public Invoice create(String userID, Integer amount) {
        // TODO(Pedro): create the real PDF
        return new Invoice(userID, amount, "http://www.africau.edu/images/default/sample.pdf");
    }


}
