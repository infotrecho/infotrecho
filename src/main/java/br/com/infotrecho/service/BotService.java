package br.com.infotrecho.service;

import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.utils.Properties;
import org.springframework.stereotype.Service;

import static br.com.infotrecho.utils.Properties.*;

@Service
public class BotService {

    private final Properties properties;

    private final MessageService messageService;

    public BotService(Properties properties, MessageService messageService) {
        this.properties = properties;
        this.messageService = messageService;
    }

    public MessageGroup welcomeMessage() {

        String welcome = properties.getText(WELCOME);

        messageService.startMessage();
        return messageService.onlyQuickText(welcome);
    }

    public MessageGroup menu() {

        messageService.startMessage();
        messageService.addText(properties.getText(_2_TEXT));
        messageService.addButton(properties.getText(_2_OPTION_GOTO_3), "http://ce1e7d26e35b.ngrok.io/bot/end");
        messageService.addButton(properties.getText(_2_OPTION_GOTO_4), "http://ce1e7d26e35b.ngrok.io/bot/ask-start");
        messageService.addButton(properties.getText(_2_OPTION_GOTO_5), "http://ce1e7d26e35b.ngrok.io/bot/end");

        return messageService.getMessageGroup();
    }

    public MessageGroup askStart() {
        String text = properties.getText(_3_TEXT);

        messageService.startMessage();
        return messageService.onlyQuickText(text);
    }

    public MessageGroup askEnd() {
        String text = properties.getText(_4_TEXT);

        messageService.startMessage();
        return messageService.onlyQuickText(text);
    }

    public MessageGroup trackAskInfo() {

        messageService.startMessage();
        messageService.addText(properties.getText(_5_TEXT));
        messageService.addButton(properties.getText(_2_OPTION_GOTO_3), "http://ce1e7d26e35b.ngrok.io/bot/end");
        messageService.addButton(properties.getText(_2_OPTION_GOTO_4), "http://ce1e7d26e35b.ngrok.io/bot/end");
        messageService.addButton(properties.getText(_2_OPTION_GOTO_5), "http://ce1e7d26e35b.ngrok.io/bot/end");

        return messageService.getMessageGroup();
    }

    public MessageGroup end() {
        messageService.startMessage();
        return messageService.onlyQuickText("FIM");
    }



}
