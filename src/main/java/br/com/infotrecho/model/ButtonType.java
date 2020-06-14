package br.com.infotrecho.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ButtonType {

    URL("web_url"), BLOCK("show_block"), JSON_URL("json_plugin_url");

    String description;

    ButtonType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
