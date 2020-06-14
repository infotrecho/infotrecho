package br.com.infotrecho.controller;

import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.service.BotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bot")
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @GetMapping
    public ResponseEntity<MessageGroup> welcome() {
        return ResponseEntity.ok(botService.welcomeMessage());
    }

    @GetMapping("/menu")
    public ResponseEntity<MessageGroup> menu() {
        return ResponseEntity.ok(botService.menu());
    }

    @GetMapping("/ask-start")
    public ResponseEntity<MessageGroup> askStart() {
        return ResponseEntity.ok(botService.askStart());
    }

    @GetMapping("/ask-end")
    public ResponseEntity<MessageGroup> askEnd() {
        return ResponseEntity.ok(botService.askEnd());
    }

    @GetMapping("/ask-track-info")
    public ResponseEntity<MessageGroup> askTrackInfo() {
        return ResponseEntity.ok(botService.askTrackInfo());
    }

    @GetMapping("/ask-track-info-location")
    public ResponseEntity<MessageGroup> askTrackInfoLocation() {
        return ResponseEntity.ok(botService.askTrackInfoLocation());
    }

    @GetMapping("/ask-restaurants")
    public ResponseEntity<MessageGroup> showRestaurants(@RequestParam String closer) {
        return ResponseEntity.ok(botService.showRestaurants(closer));
    }

    @GetMapping("/show-restaurant-details")
    public ResponseEntity<MessageGroup> showRestaurantDetails(@RequestParam String id) {
        return ResponseEntity.ok(botService.showRestaurantDetails(id));
    }

    @GetMapping("/ask-information-ok")
    public ResponseEntity<MessageGroup> askInformationOk() {
        return ResponseEntity.ok(botService.askInformationOk());
    }

    @GetMapping("/ask-to-collaborate")
    public ResponseEntity<MessageGroup> askToCollaborate() {
        return ResponseEntity.ok(botService.askToColaboratte());
    }

    @GetMapping("/ending")
    public ResponseEntity<MessageGroup> ending() {
        return ResponseEntity.ok(botService.ending());
    }
}
