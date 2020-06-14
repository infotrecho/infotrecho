package br.com.infotrecho.client.infotrecho.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TripRequest {
    @JsonProperty("messenger_id")
    private String messengerId;
    private String origin;
    private String destination;
    @JsonProperty("origin_geocode")
    private List<BigDecimal> originGeocode;
    @JsonProperty("destination_geocode")
    private List<BigDecimal> destinationGeocode;
}
