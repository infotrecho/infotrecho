package br.com.infotrecho.controller;

import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.service.BotService;
import br.com.infotrecho.service.DriversService;
import br.com.infotrecho.service.InteractiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/infotrecho")
public class InfoTrechoController {

    private final DriversService driversService;

    private final InteractiveService interactiveService;

    public InfoTrechoController(DriversService driversService, InteractiveService interactiveService) {
        this.driversService = driversService;
        this.interactiveService = interactiveService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<MessageGroup> saveDriver(@RequestBody final DriverRequest driverRequest) throws IOException {
        driversService.saveDriver(driverRequest);
        return ResponseEntity.ok(interactiveService.afterSave());
    }

    @PostMapping("/trips")
    public ResponseEntity<TripResponse> saveTrip(@RequestBody final TripRequest tripRequest) throws IOException {
        return ResponseEntity.ok(driversService.saveTrip(tripRequest));
    }
}
