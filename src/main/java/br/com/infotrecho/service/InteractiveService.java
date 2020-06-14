package br.com.infotrecho.service;

import br.com.infotrecho.model.MessageGroup;
import org.springframework.stereotype.Service;

@Service
public class InteractiveService {

    private final MessageService messageService;

    private final CentralService centralService;

    public InteractiveService(MessageService messageService, CentralService centralService) {
        this.messageService = messageService;
        this.centralService = centralService;
    }

    public MessageGroup afterSave() {
        messageService.startMessage();
        return messageService.onlyQuickText("Ok!");
    }
}
