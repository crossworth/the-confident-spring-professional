package br.dev.pedro.pdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class InvoiceDTO {
    @JsonProperty("user_id")
    @NotBlank
    private String userID;

    @Min(10)
    @Max(50)
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
