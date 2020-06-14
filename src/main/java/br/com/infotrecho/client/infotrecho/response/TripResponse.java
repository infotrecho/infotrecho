package br.com.infotrecho.client.infotrecho.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TripResponse {

    private String id;
    private String origin;
    private String destination;
    @JsonProperty("origin_geocode")
    private BigDecimal[] originGeocode;
    @JsonProperty("destination_geocode")
    private BigDecimal[] destinationGeocode;
    @JsonProperty("driver_id")
    private String driverId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
