package br.com.infotrecho.client.infotrecho.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventResponse {
    private String id;
    private String description;
    private String classification;
    private String metadata;
    private Integer priority;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
