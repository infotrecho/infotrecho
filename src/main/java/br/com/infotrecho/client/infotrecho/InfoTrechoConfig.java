package br.com.infotrecho.client.infotrecho;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class InfoTrechoConfig {

    public final static String DRIVERS_PATH = "drivers";
    public final static String TRIPS_PATH = "trips";
    public final static String EVENT_PATH = "events";

    @Value("${infotrecho.scheme}")
    private String scheme;

    @Value("${infotrecho.domain}")
    private String domain;

    @Value("${infotrecho.scheme}://${infotrecho.domain}")
    private String host;

    @Value("${infotrecho.context}")
    private String context;
}
