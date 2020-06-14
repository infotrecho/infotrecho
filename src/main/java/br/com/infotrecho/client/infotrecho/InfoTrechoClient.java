package br.com.infotrecho.client.infotrecho;

import br.com.infotrecho.client.GenericClient;
import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import lombok.extern.log4j.Log4j2;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class InfoTrechoClient extends GenericClient {

    private final InfoTrechoConfig infoTrechoConfig;

    public InfoTrechoClient(@Autowired InfoTrechoConfig infoTrechoConfig) {
        this.infoTrechoConfig = infoTrechoConfig;
    }

    public DriverResponse saveDriver(DriverRequest driverRequest) throws IOException {
        final var url = new HttpUrl.Builder()
                .scheme(infoTrechoConfig.getScheme())
                .host(infoTrechoConfig.getDomain())
                .addPathSegment(infoTrechoConfig.getContext())
                .addPathSegment(InfoTrechoConfig.DRIVERS_PATH)
                .build();

        var request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_JSON, this.getObjectMapper().writeValueAsString(driverRequest)))
                .build();


        try (var response = this.getHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorResponse = response.body().string();
                log.error("==== Erro retornado da InfoTrechoPAI. Code: {} Response: {}", response.code(), errorResponse);
                throw new RuntimeException("Erro ao executar endpoint drivers");
            } else {
                return this.getObjectMapper().readValue(response.body().byteStream(), DriverResponse.class);
            }
        }
    }

    public TripResponse saveTrip(TripRequest tripRequest) throws IOException {
        final var url = new HttpUrl.Builder()
                .scheme(infoTrechoConfig.getScheme())
                .host(infoTrechoConfig.getDomain())
                .addPathSegment(infoTrechoConfig.getContext())
                .addPathSegment(InfoTrechoConfig.TRIPS_PATH)
                .build();

        var request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_JSON, this.getObjectMapper().writeValueAsString(tripRequest)))
                .build();


        try (var response = this.getHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorResponse = response.body().string();
                log.error("==== Erro retornado da InfoTrechoPAI. Code: {} Response: {}", response.code(), errorResponse);
                throw new RuntimeException("Erro ao executar endpoint trips");
            } else {
                return this.getObjectMapper().readValue(response.body().byteStream(), TripResponse.class);
            }
        }
    }
}
