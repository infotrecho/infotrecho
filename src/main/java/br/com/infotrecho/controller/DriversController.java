package br.com.infotrecho.controller;

import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import br.com.infotrecho.service.DriversService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class DriversController {

    private final DriversService driversService;

    public DriversController(DriversService driversService) {
        this.driversService = driversService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<DriverResponse> saveDriver(@RequestBody final DriverRequest driverRequest) throws IOException {
        return ResponseEntity.ok(driversService.saveDriver(driverRequest));
    }

    @PostMapping("/trips")
    public ResponseEntity<TripResponse> saveTrip(@RequestBody final TripRequest tripRequest) throws IOException {
        return ResponseEntity.ok(driversService.saveTrip(tripRequest));
    }
}
