package br.com.infotrecho.controller;

import br.com.infotrecho.client.infotrecho.request.DriverRequest;
import br.com.infotrecho.client.infotrecho.request.TripRequest;
import br.com.infotrecho.client.infotrecho.response.DriverResponse;
import br.com.infotrecho.client.infotrecho.response.EventResponse;
import br.com.infotrecho.client.infotrecho.response.TripResponse;
import br.com.infotrecho.service.InfoTrechoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/infotrecho")
public class InfoTrechoController {

    private final InfoTrechoService infoTrechoService;

    public InfoTrechoController(InfoTrechoService infoTrechoService) {
        this.infoTrechoService = infoTrechoService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<DriverResponse> saveDriver(@RequestBody final DriverRequest driverRequest) throws IOException {
        return ResponseEntity.ok(infoTrechoService.saveDriver(driverRequest));
    }

    @PostMapping("/trips")
    public ResponseEntity<TripResponse> saveTrip(@RequestBody final TripRequest tripRequest) throws IOException {
        return ResponseEntity.ok(infoTrechoService.saveTrip(tripRequest));
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventResponse>> listEvents(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude) throws IOException {
        return ResponseEntity.ok(infoTrechoService.listEvents(latitude, longitude));
    }
}
