package br.com.infotrecho.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restaurant {

    private String name;
    private Integer ranking;
    private String address;
    private String latitude;
    private String longitude;

}
