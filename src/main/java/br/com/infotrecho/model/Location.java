package br.com.infotrecho.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private String id;
    private String name;

}
