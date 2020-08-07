package br.dev.pedro.mybank.dto;

import java.util.List;

public class ErrorDTO {
    private String message;
    private List<String> fields;

    public ErrorDTO(String message, List<String> fields) {
        this.message = message;
        this.fields = fields;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
