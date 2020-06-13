package com.boaviagem.boaviagem.service;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.MessagingType;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
public class MessageService {

    private Messenger messenger;

    public MessageService(Messenger messenger) {
        this.messenger = messenger;
    }

    public void handleTextMessageEvent(TextMessageEvent event) {
        log.debug("Received TextMessageEvent: {}", event);

        final String messageId = event.messageId();
        final String messageText = event.text();
        final String senderId = event.senderId();
        final Instant timestamp = event.timestamp();

        log.info("Received message '{}' with text '{}' from user '{}' at '{}'", messageId, messageText, senderId, timestamp);

        try {
             sendTextMessage(senderId, messageText);
        } catch (Exception e) {
            handleSendException(e);
        }
    }

    private void handleSendException(Exception e) {
        log.error("Message could not be sent. An unexpected error occurred.", e);
    }

    private void sendTextMessage(String recipientId, String text) {
        try {
            final IdRecipient recipient = IdRecipient.create(recipientId);
            final NotificationType notificationType = NotificationType.REGULAR;
            final String metadata = "DEVELOPER_DEFINED_METADATA";

            final TextMessage textMessage = TextMessage.create(text, Optional.empty(), Optional.of(metadata));
            final MessagePayload messagePayload = MessagePayload.create(recipient, MessagingType.RESPONSE, textMessage,
                    Optional.of(notificationType), Optional.empty());
            this.messenger.send(messagePayload);
        } catch (MessengerApiException | MessengerIOException e) {
            handleSendException(e);
        }
    }
}
