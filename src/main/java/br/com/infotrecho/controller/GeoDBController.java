package br.com.infotrecho.controller;

import br.com.infotrecho.client.geodb.response.CityDataResponse;
import br.com.infotrecho.service.CityLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/geodb")
public class GeoDBController {

    private final CityLocationService cityLocationService;

    public GeoDBController(CityLocationService cityLocationService) {
        this.cityLocationService = cityLocationService;
    }

    @GetMapping
    public ResponseEntity<List<CityDataResponse>> getCityData(@PathParam("city") String city) throws IOException {
        return ResponseEntity.ok(cityLocationService.getCityData(city));
    }
}
