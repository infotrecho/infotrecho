package br.com.infotrecho.client.infotrecho.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverResponse {
    private String id;
    private String name;
    private String phone;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private Boolean trusted;
    @JsonProperty("messenger_id")
    private String messengerId;
}
