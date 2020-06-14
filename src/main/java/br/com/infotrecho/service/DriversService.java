package br.com.infotrecho.service;

import br.com.infotrecho.client.infotrecho.InfoTrechoClient;
import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class DriversService {
    private final InfoTrechoClient infoTrechoClient;

    public DriversService(InfoTrechoClient infoTrechoClient) {
        this.infoTrechoClient = infoTrechoClient;
    }

    public DriverResponse saveDriver(DriverRequest driverRequest) throws IOException {
        log.info("Save driver {}", driverRequest);
        return infoTrechoClient.saveDriver(driverRequest);
    }

    public TripResponse saveTrip(TripRequest tripRequest) throws IOException {
        log.info("Save trip {}", tripRequest);
        return infoTrechoClient.saveTrip(tripRequest);
    }
}
