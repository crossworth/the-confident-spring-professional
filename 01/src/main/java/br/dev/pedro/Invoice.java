package br.dev.pedro;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Invoice {
    private String id;

    @JsonProperty("user_id")
    private String userID;

    @JsonProperty("pdf_url")
    private String pdfURL;

    private Integer amount;

    public Invoice() {
    }

    public Invoice(String userID, Integer amount, String pdfURL) {
        this.id = UUID.randomUUID().toString();
        this.userID = userID;
        this.pdfURL = pdfURL;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
