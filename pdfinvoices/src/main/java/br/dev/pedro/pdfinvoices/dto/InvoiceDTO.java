package br.dev.pedro.pdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDTO {
    @JsonProperty("user_id")
    private String userID;

    private Integer amount;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
