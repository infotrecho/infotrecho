package br.com.infotrecho.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String messengerId;
    private String firstName;
    private String lastName;

    private String lastTrackStart;
    private String lastTrackEnd;

}
