package br.com.infotrecho.controller;

import br.com.infotrecho.response.ButtonsResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bot")
public class BotController {

    @GetMapping("/postos")
    public ResponseEntity<ButtonsResponse> verifyWebHook() {
        log.info("Foiiiiiii......");
        return ResponseEntity.ok(
                ButtonsResponse
                        .builder()
                        .messages(List.of(
                                ButtonsResponse.Messages.builder()
                                        .attachment(
                                                ButtonsResponse.Messages.Attachment.builder()
                                                        .payload(
                                                                ButtonsResponse.Messages.Attachment.Payload.builder()
                                                                        .text("Agora vai")
                                                                        .buttons(List.of(
                                                                                ButtonsResponse.Messages.Attachment.Payload.Button.builder()
                                                                                        .title("Primeiro")
                                                                                        .url("http://www.uol.com.br")
                                                                                        .build(),
                                                                                ButtonsResponse.Messages.Attachment.Payload.Button.builder()
                                                                                        .title("Segundo")
                                                                                        .url("http://www.terra.com.br")
                                                                                        .build(),
                                                                                ButtonsResponse.Messages.Attachment.Payload.Button.builder()
                                                                                        .title("Terceiro")
                                                                                        .url("http://www.ig.com.br")
                                                                                        .build()
                                                                        ))
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build())
                        )
                        .build()
        );
    }
}
