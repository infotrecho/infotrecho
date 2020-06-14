package br.com.infotrecho.service;

import br.com.infotrecho.model.Location;
import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.model.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BotService {

    private final MessageService messageService;

    private final CentralService centralService;

    private String url = "http://96dc3da5fa0e.ngrok.io";

    public BotService(MessageService messageService, CentralService centralService) {
        this.messageService = messageService;
        this.centralService = centralService;
    }

    public MessageGroup welcomeMessage() {
        log.info("Welcome message as called");

        messageService.startMessage();
        return messageService.onlyTwoText("Bem vindo ao InfoTrecho, seu companheiro da estrada.",
                "Fale comigo sempre que precisar de informações sobre seu percurso.");
    }

    public MessageGroup menu() {
        log.info("Menu message as called");

        messageService.startMessage();
        messageService.addText("Como posso te ajudar?");
        messageService.addButton("Informar ocorrência", url + "/bot/end");
        messageService.addButton("Informações do trecho", url + "/bot/ask-start");
        messageService.addButton("Informações de saúde", url + "/bot/end");

        return messageService.getMessageGroup();
    }

    public MessageGroup askStart() {
        log.info("Asking city origin message as called");

        messageService.startMessage();
        return messageService.onlyQuickText("Informe a cidade de partida.");
    }

    public MessageGroup askEnd() {
        log.info("Asking city destiny message as called");

        messageService.startMessage();
        return messageService.onlyQuickText("Informe a cidade de destino.");
    }

    public MessageGroup askTrackInfo() {
        log.info("Asking what information as called");

        messageService.startMessage();
        messageService.addText("Que tipo de informação você gostaria de saber?");
        messageService.addButton("Postos e Restaurantes", url + "/bot/ask-track-info-location");
        messageService.addButton("Pontos de pernoite", url + "/bot/end");
        messageService.addButton("Serviços públicos", url + "/bot/end");

        return messageService.getMessageGroup();
    }

    public MessageGroup askTrackInfoLocation() {
        log.info("Asking where information as called");

        messageService.startMessage();
        messageService.addText("Onde?");
        messageService.addButton("Na cidade", url + "/bot/ask-restaurants?closer=city");
        messageService.addButton("No percurso", url + "/bot/ask-restaurants?closer=route");

        return messageService.getMessageGroup();
    }

    public MessageGroup showRestaurants(String closer) {
        log.info("Asking to show restaurants as called");

        List<Location> locations = centralService.findRestaurants(closer);

        messageService.startMessage();
        messageService.addText("Estes são os postos e restaurantes que recomendamos no seu trecho:");
        messageService.addButton(locations.get(0).getName(), url + "/bot/show-restaurant-details?id=" + locations.get(0).getId());
        messageService.addButton(locations.get(1).getName(), url + "/bot/show-restaurant-details?id=" + locations.get(0).getId());
        messageService.addButton(locations.get(2).getName(), url + "/bot/show-restaurant-details?id=" + locations.get(0).getId());

        return messageService.getMessageGroup();
    }


    public MessageGroup showRestaurantDetails(String id) {
        log.info("Asking to show restaurant details as called");

        Restaurant restaurant = centralService.findRestaurant(id);

        messageService.startMessage();
        messageService.addText("[" + restaurant.getName() + "] " + restaurant.getAddress() + " " + restaurant.getRanking() + "/5");
        messageService.addButton("Ir para Google Maps", "https://www.google.com.br/maps/@" + restaurant.getLatitude() + "," + restaurant.getLongitude(), true);
        messageService.addButton("Compartilhar a localização", url + "/bot/ask-information-ok");
        messageService.addQuickReply("Continuar", url + "/bot/ask-information-ok");

        return messageService.getMessageGroup();
    }

    public MessageGroup askInformationOk() {
        log.info("Asking if information is ok as called");

        messageService.startMessage();
        messageService.addText("As informações foram úteis?");
        messageService.addButton("Sim :)", url + "/bot/ask-to-collaborate");
        messageService.addButton("Não :(", url + "/bot/ask-to-collaborate");

        return messageService.getMessageGroup();
    }

    public MessageGroup askToColaboratte() {
        log.info("Asking if information is ok as called");

        messageService.startMessage();
        messageService.addText("Sabia que colaborando com informações novas sobre o trecho você ganha pontos que valem prêmios?");
        messageService.addButton("Colaborar", url + "/bot/ending");
        messageService.addButton("Encerrar", url + "/bot/ending");

        return messageService.getMessageGroup();
    }

    public MessageGroup ending() {
        messageService.startMessage();
        return messageService.onlyTwoText("O InfoTrecho deseja uma boa viagem!", "Caso precise falar com a gente é só dizer um oi!");
    }

}
