package br.com.infotrecho.service;

import br.com.infotrecho.client.geodb.response.CityDataResponse;
import br.com.infotrecho.client.infotrecho.InfoTrechoClient;
import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class DriversService {
    private final InfoTrechoClient infoTrechoClient;
    private final CityLocationService cityLocationService;

    public DriversService(InfoTrechoClient infoTrechoClient, CityLocationService cityLocationService) {
        this.infoTrechoClient = infoTrechoClient;
        this.cityLocationService = cityLocationService;
    }

    public DriverResponse saveDriver(DriverRequest driverRequest) throws IOException {
        log.info("Save driver {}", driverRequest);
        return infoTrechoClient.saveDriver(driverRequest);
    }

    public TripResponse saveTrip(TripRequest tripRequest) throws IOException {
        log.info("Save trip {}", tripRequest);

        CityDataResponse cityOrigin = cityLocationService.getCityDataFromRegionCode(tripRequest.getOrigin(), tripRequest.getOriginRegionCode());
        CityDataResponse cityDestination = cityLocationService.getCityDataFromRegionCode(tripRequest.getDestination(), tripRequest.getDestinationRegionCode());

        List<BigDecimal> destinationCoords = Arrays.asList(cityDestination.getLatitude(), cityDestination.getLongitude());
        List<BigDecimal> originCoords = Arrays.asList(cityOrigin.getLatitude(), cityOrigin.getLongitude());

        tripRequest.setDestinationGeocode(destinationCoords);
        tripRequest.setOriginGeocode(originCoords);

        return infoTrechoClient.saveTrip(tripRequest);
    }
}
