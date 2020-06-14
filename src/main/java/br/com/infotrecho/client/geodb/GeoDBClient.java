package br.com.infotrecho.client.geodb;

import br.com.infotrecho.client.GenericClient;
import br.com.infotrecho.client.geodb.response.GeoDbCitiesResponse;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class GeoDBClient extends GenericClient {

    private final GeoDBConfig geoDBConfig;

    public GeoDBClient(@Autowired final GeoDBConfig geoDBConfig) {
        this.geoDBConfig = geoDBConfig;
    }

    public GeoDbCitiesResponse getCitiesData(String city) throws IOException {

        final var url = new HttpUrl.Builder()
                .scheme(geoDBConfig.getScheme())
                .host(geoDBConfig.getDomain())
                .addPathSegment(geoDBConfig.getVersion())
                .addPathSegment(geoDBConfig.GEO_PATH)
                .addPathSegment("cities")
                .addQueryParameter("countryIds", "BR")
                .addQueryParameter("namePrefix", city)
                .build();

        Request request = new Request.Builder()
                .addHeader("X-RapidAPI-Key", geoDBConfig.getApiKey())
                .url(url)
                .get()
                .build();
        Response response = this.getHttpClient().newCall(request).execute();

        return this.getObjectMapper().readValue(response.body().string(),
                GeoDbCitiesResponse.class);
    }
}
