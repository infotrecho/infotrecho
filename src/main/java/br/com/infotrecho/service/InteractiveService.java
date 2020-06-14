package br.com.infotrecho.service;

import br.com.infotrecho.model.MessageGroup;
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

    public InteractiveService(Properties properties, MessageService messageService) {
        this.properties = properties;
        this.messageService = messageService;
    }

    public MessageGroup track() {

        String track = properties.getText(_1_TEXT);

        messageService.startMessage();
        return messageService.onlyQuickText(track);
    }

    public MessageGroup save(String start, String end) {

        messageService.startMessage();
        return messageService.onlyQuickText("Ok!");
    }
}
