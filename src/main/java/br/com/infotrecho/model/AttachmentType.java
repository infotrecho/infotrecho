package br.com.infotrecho.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AttachmentType {

    TEMPLATE("template"), IMAGE("image"), AUDIO("audio"), VIDEO("video"), FILE("file");

    String description;

    AttachmentType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
