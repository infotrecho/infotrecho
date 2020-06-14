package br.com.infotrecho.service;

import br.com.infotrecho.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<MessageGroup.Messages> messagesList;
    private MessageGroup.Messages.Attachment.Payload payload;
    private MessageGroup.Messages.Attachment attachment;
    private MessageGroup.Messages messages;

    public void startMessage() {
        messagesList = new ArrayList<>();
        attachment = new MessageGroup.Messages.Attachment();
        payload = new MessageGroup.Messages.Attachment.Payload();
        messages = new MessageGroup.Messages();
    }

    private void registerPayload() {

        attachment = new MessageGroup.Messages.Attachment();
        attachment.setType(AttachmentType.TEMPLATE);
        attachment.setPayload(payload);

        if (!messagesList.isEmpty()) {
            messages = messagesList.get(0);
        }
        messagesList = new ArrayList<>();

        messages.setAttachment(attachment);
        messagesList.add(messages);
    }

    public void addText(String text) {
        payload.setTemplateType(PayloadType.GENERIC);
        payload.setText(text);
    }

    public void addQuickReply(String title, String destination) {

        messages = new MessageGroup.Messages();

        MessageGroup.Messages.QuickReply quickReply = new MessageGroup.Messages.QuickReply();
        quickReply.setTitle(title);
        quickReply.setButtonType(ButtonType.JSON_URL);
        quickReply.setUrl(destination);

        messages.addQuickReply(quickReply);

        messagesList.add(messages);
    }

    public MessageGroup onlyQuickText(String text) {
        MessageGroup.Messages messages = new MessageGroup.Messages();
        messages.setText(text);
        messagesList.add(messages);
        return new MessageGroup(messagesList);
    }

    public MessageGroup onlyTwoText(String text1, String text2) {
        MessageGroup.Messages messages1 = new MessageGroup.Messages();
        messages1.setText(text1);
        messagesList.add(messages1);
        MessageGroup.Messages messages2 = new MessageGroup.Messages();
        messages2.setText(text2);
        messagesList.add(messages2);
        return new MessageGroup(messagesList);
    }

    public void addButton(String textButton,  String destination, boolean isUrl) {

        payload.setTemplateType(PayloadType.BUTTON);

        MessageGroup.Messages.Attachment.Payload.Button button = new MessageGroup.Messages.Attachment.Payload.Button();
        button.setTitle(textButton);
        button.setType(isUrl ? ButtonType.URL : ButtonType.JSON_URL);
        button.setUrl(destination);
        payload.addButton(button);
    }

    public void addImage(String textButton,  String destination) {

        MessageGroup.Messages.Attachment.Payload.Element element = new MessageGroup.Messages.Attachment.Payload.Element();
        element.setType(MediaType.IMAGE);
        element.setUrl(destination);

        MessageGroup.Messages.Attachment.Payload.Button button = new MessageGroup.Messages.Attachment.Payload.Button();
        button.setTitle(textButton);
        button.setType(ButtonType.JSON_URL);
        button.setUrl(destination);

        element.addButton(button);

        payload.setTemplateType(PayloadType.ITENS_LIST);
        payload.addElement(element);
    }

    public void addButton(String textButton,  String destination) {
        addButton(textButton,  destination, false);
    }

    public MessageGroup getMessageGroup() {
        registerPayload();
        return new MessageGroup(messagesList);
    }
}
