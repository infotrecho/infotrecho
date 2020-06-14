package br.com.infotrecho.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MediaType {


    IMAGE("image"), VIDEO("video");

    String description;

    MediaType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
