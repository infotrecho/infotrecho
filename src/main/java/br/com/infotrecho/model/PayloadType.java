package br.com.infotrecho.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PayloadType {

    BUTTON("button"), CONFIRMATION("receipt"), ITENS_LIST("list"), GALLERY("receipt"), GENERIC("generic");

    String description;

    PayloadType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
