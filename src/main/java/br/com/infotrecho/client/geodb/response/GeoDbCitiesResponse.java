package br.com.infotrecho.client.geodb.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class GeoDbCitiesResponse {
    private List<Data> data;

    @Builder
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private String name;
        private String regionCode;
        private BigDecimal latitude;
        private BigDecimal longitude;
    }
}
