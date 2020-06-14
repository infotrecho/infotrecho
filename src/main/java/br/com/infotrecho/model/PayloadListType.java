package br.com.infotrecho.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PayloadListType {

    LARGE("large"), COMPACT("compact");

    String description;

    PayloadListType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
