package br.com.infotrecho.client.infotrecho.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {

    private String name;
    @JsonProperty("messenger_id")
    private String messengerId;
}