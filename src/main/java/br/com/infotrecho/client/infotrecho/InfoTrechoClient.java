package br.com.infotrecho.client.infotrecho;

import br.com.infotrecho.client.GenericClient;
import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.EventResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.log4j.Log4j2;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class InfoTrechoClient extends GenericClient {

    private final InfoTrechoConfig infoTrechoConfig;

    public InfoTrechoClient(@Autowired InfoTrechoConfig infoTrechoConfig) {
        this.infoTrechoConfig = infoTrechoConfig;
    }

    public DriverResponse saveDriver(DriverRequest driverRequest) throws IOException {
        final HttpUrl url = new HttpUrl.Builder()
                .scheme(infoTrechoConfig.getScheme())
                .host(infoTrechoConfig.getDomain())
                .addPathSegment(infoTrechoConfig.getContext())
                .addPathSegment(InfoTrechoConfig.DRIVERS_PATH)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_JSON, this.getObjectMapper().writeValueAsString(driverRequest)))
                .build();


        try (Response response = this.getHttpClient().newCall(request).execute()) {
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
        final HttpUrl url = new HttpUrl.Builder()
                .scheme(infoTrechoConfig.getScheme())
                .host(infoTrechoConfig.getDomain())
                .addPathSegment(infoTrechoConfig.getContext())
                .addPathSegment(InfoTrechoConfig.TRIPS_PATH)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_JSON, this.getObjectMapper().writeValueAsString(tripRequest)))
                .build();


        try (Response response = this.getHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorResponse = response.body().string();
                log.error("==== Erro retornado da InfoTrechoPAI. Code: {} Response: {}", response.code(), errorResponse);
                throw new RuntimeException("Erro ao executar endpoint trips");
            } else {
                return this.getObjectMapper().readValue(response.body().byteStream(), TripResponse.class);
            }
        }
    }

    public List<EventResponse> listEvents(String latitude, String longitude) throws IOException {
        final HttpUrl url = new HttpUrl.Builder()
                .scheme(infoTrechoConfig.getScheme())
                .host(infoTrechoConfig.getDomain())
                .addPathSegment(infoTrechoConfig.getContext())
                .addPathSegment(InfoTrechoConfig.EVENT_PATH)
                .addQueryParameter("latitude", latitude)
                .addQueryParameter("longitude", longitude)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();


        try (Response response = this.getHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorResponse = response.body().string();
                log.error("==== Erro retornado da InfoTrechoPAI. Code: {} Response: {}", response.code(), errorResponse);
                throw new RuntimeException("Erro ao executar endpoint trips");
            } else {
                return this.getObjectMapper().readValue(response.body().byteStream(), new TypeReference<List<EventResponse>>() {
                });
            }
        }
    }
}
