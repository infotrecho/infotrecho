package br.com.infotrecho.service;

import br.com.infotrecho.client.geodb.GeoDBClient;
import br.com.infotrecho.client.geodb.response.CityDataResponse;
import br.com.infotrecho.client.geodb.response.GeoDbCitiesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CityLocationService {

    private final GeoDBClient geoDBClient;

    public CityLocationService(GeoDBClient geoDBClient) {
        this.geoDBClient = geoDBClient;
    }

    public List<CityDataResponse> getCityData(String city) throws IOException {
        log.info("Find city {}", city);
        GeoDbCitiesResponse data = geoDBClient.getCitiesData(city);
        List<CityDataResponse> cities = new ArrayList<>();
        data.getData().forEach(cityData -> {
            if (cityData.getName().equalsIgnoreCase(city)) {
                cities.add(CityDataResponse
                        .builder()
                        .name(cityData.getName())
                        .latitude(cityData.getLatitude())
                        .longitude(cityData.getLongitude())
                        .regionCode(cityData.getRegionCode())
                        .build()
                );
            }
        });
        return cities;
    }
}
