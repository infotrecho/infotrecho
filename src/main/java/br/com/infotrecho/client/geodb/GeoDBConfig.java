package br.com.infotrecho.client.geodb;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class GeoDBConfig {

    public final static String GEO_PATH = "geo";

    @Value("${geodb.scheme}")
    private String scheme;

    @Value("${geodb.domain}")
    private String domain;

    @Value("${geodb.scheme}://${geodb.domain}")
    private String host;

    @Value("${geodb.version}")
    private String version;

    @Value("${geodb.api-key}")
    private String apiKey;
}
