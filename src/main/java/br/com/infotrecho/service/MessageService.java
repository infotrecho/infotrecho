package br.com.infotrecho.service;

import br.com.infotrecho.model.AttachmentType;
import br.com.infotrecho.model.ButtonType;
import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.model.PayloadType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<MessageGroup.Messages> messagesList;
    private MessageGroup.Messages.Attachment.Payload payload;
    private MessageGroup.Messages.Attachment attachment;

    public void startMessage() {
        messagesList = new ArrayList<>();
        attachment = new MessageGroup.Messages.Attachment();
        payload = new MessageGroup.Messages.Attachment.Payload();
    }

    private void registerPayload() {

        MessageGroup.Messages.Attachment attachment = new MessageGroup.Messages.Attachment();
        attachment.setType(AttachmentType.TEMPLATE);
        attachment.setPayload(payload);

        MessageGroup.Messages messages = new MessageGroup.Messages();
        messages.setAttachment(attachment);

        messagesList.add(messages);
    }

    public void addText(String text) {
        payload.setTemplateType(PayloadType.GENERIC);
        payload.setText(text);
    }

    public MessageGroup onlyQuickText(String text) {
        MessageGroup.Messages messages = new MessageGroup.Messages();
        messages.setText(text);
        messagesList.add(messages);
        return new MessageGroup(messagesList);
    }

    public void addButton(String text,  String destination, boolean isUrl) {

        payload.setTemplateType(PayloadType.BUTTON);

        MessageGroup.Messages.Attachment.Payload.Button button = new MessageGroup.Messages.Attachment.Payload.Button();
        button.setTitle(text);
        button.setType(isUrl ? ButtonType.URL : ButtonType.JSON_URL);
        button.setUrl(destination);
        payload.addButton(button);
    }

    public void addButton(String text,  String destination) {
        addButton(text,  destination, false);
    }

    public MessageGroup getMessageGroup() {
        registerPayload();
        return new MessageGroup(messagesList);
    }
}
