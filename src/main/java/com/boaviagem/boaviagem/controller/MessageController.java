package com.boaviagem.boaviagem.controller;

import static com.github.messenger4j.Messenger.CHALLENGE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.MODE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.SIGNATURE_HEADER_NAME;
import static com.github.messenger4j.Messenger.VERIFY_TOKEN_REQUEST_PARAM_NAME;

import com.boaviagem.boaviagem.service.MessageService;
import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    private Messenger messenger;

    private MessageService messageService;

    public MessageController(Messenger messenger, MessageService messageService) {
        this.messenger = messenger;
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<String> verifyWebHook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
                                                @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
                                                @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
        log.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode, verifyToken, challenge);
        try {
            this.messenger.verifyWebhook(mode, verifyToken);
            return ResponseEntity.ok(challenge);
        } catch (MessengerVerificationException e) {
            log.warn("Webhook verification failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Void> handleCallback(@RequestBody final String payload,
                                               @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        log.debug("Received Messenger Platform callback - payload: {} | signature: {}", payload, signature);
        try {
            this.messenger.onReceiveEvents(payload, Optional.of(signature), event -> {
                if (event.isTextMessageEvent()) {
                    this.messageService.handleTextMessageEvent(event.asTextMessageEvent());
                }
            });
            log.debug("Processed callback payload successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessengerVerificationException e) {
            log.warn("Processing of callback payload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
