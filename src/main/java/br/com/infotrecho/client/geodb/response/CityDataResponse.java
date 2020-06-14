package br.com.infotrecho.client.geodb.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
public class CityDataResponse {
    private String name;
    private String regionCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
