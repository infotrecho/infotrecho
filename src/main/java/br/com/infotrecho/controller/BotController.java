package br.com.infotrecho.controller;

import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.service.BotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return ResponseEntity.ok(botService.trackAskInfo());
    }

}
