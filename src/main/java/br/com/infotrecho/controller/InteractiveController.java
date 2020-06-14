package br.com.infotrecho.controller;

import br.com.infotrecho.controller.request.UserRequest;
import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.service.InteractiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/interactive")
public class InteractiveController {

    private final InteractiveService trackService;

    public InteractiveController(InteractiveService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<MessageGroup> track() {
        return ResponseEntity.ok(trackService.track());
    }

    @PostMapping(value = "/save-user")
    public ResponseEntity<MessageGroup> save(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(trackService.save(userRequest));
    }

}
