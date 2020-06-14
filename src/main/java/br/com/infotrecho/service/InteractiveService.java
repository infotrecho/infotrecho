package br.com.infotrecho.service;

import br.com.infotrecho.controller.request.UserRequest;
import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.model.User;
import br.com.infotrecho.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static br.com.infotrecho.utils.Properties._1_TEXT;

@Service
public class InteractiveService {

    Logger log = LoggerFactory.getLogger(InteractiveService.class);

    private final Properties properties;

    private final MessageService messageService;

    private final CentralService centralService;

    public InteractiveService(Properties properties, MessageService messageService, CentralService centralService) {
        this.properties = properties;
        this.messageService = messageService;
        this.centralService = centralService;
    }

    public MessageGroup track() {

        String track = properties.getText(_1_TEXT);

        messageService.startMessage();
        return messageService.onlyQuickText(track);
    }

    public MessageGroup save(UserRequest userRequest) {

        User user = User.builder()
                .firstName(userRequest.getUser().getFirstName())
                .lastName(userRequest.getUser().getLastName())
                .messengerId(userRequest.getUser().getMessengerId())
                .lastTrackStart(userRequest.getRoute().getStart())
                .lastTrackEnd(userRequest.getRoute().getEnd())
                .build();

        centralService.saveUser(user);

        messageService.startMessage();
        return messageService.onlyQuickText("Ok!");
    }
}
