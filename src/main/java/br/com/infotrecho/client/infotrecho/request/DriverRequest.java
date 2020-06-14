package br.com.infotrecho.client.infotrecho.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverRequest {
    private String name;
    @JsonProperty("messenger_id")
    private String messengerId;
}