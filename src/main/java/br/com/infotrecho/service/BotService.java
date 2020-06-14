package br.com.infotrecho.service;

import br.com.infotrecho.model.Location;
import br.com.infotrecho.model.MessageGroup;
import br.com.infotrecho.model.Restaurant;
import br.com.infotrecho.utils.Properties;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.infotrecho.utils.Properties.WELCOME;

@Service
public class BotService {

    private final Properties properties;

    private final MessageService messageService;

    private final CentralService centralService;

    public BotService(Properties properties, MessageService messageService, CentralService centralService) {
        this.properties = properties;
        this.messageService = messageService;
        this.centralService = centralService;
    }

    public MessageGroup welcomeMessage() {

        String welcome = properties.getText(WELCOME);

        messageService.startMessage();
        return messageService.onlyQuickText(welcome);
    }

    public MessageGroup menu() {

        messageService.startMessage();
        messageService.addText("Como posso te ajudar?");
        messageService.addButton("Informar ocorrência?", "http://ce1e7d26e35b.ngrok.io/bot/end");
        messageService.addButton("Informações do trecho", "http://ce1e7d26e35b.ngrok.io/bot/ask-start");
        messageService.addButton("Informações de saúde", "http://ce1e7d26e35b.ngrok.io/bot/end");

        return messageService.getMessageGroup();
    }

    public MessageGroup askStart() {
        messageService.startMessage();
        return messageService.onlyQuickText("Informe a cidade de partida.");
    }

    public MessageGroup askEnd() {
        messageService.startMessage();
        return messageService.onlyQuickText("Informe a cidade de destino.");
    }

    public MessageGroup askTrackInfo() {

        messageService.startMessage();
        messageService.addText("Que tipo de informação você gostaria de saber?");
        messageService.addButton("Postos e Restaurantes", "http://ce1e7d26e35b.ngrok.io/bot/ask-track-info-location");
        messageService.addButton("Pontos de pernoite", "http://ce1e7d26e35b.ngrok.io/bot/end");
        messageService.addButton("Serviços públicos", "http://ce1e7d26e35b.ngrok.io/bot/end");

        return messageService.getMessageGroup();
    }

    public MessageGroup askTrackInfoLocation() {

        messageService.startMessage();
        messageService.addText("Onde?");
        messageService.addButton("Na cidade", "http://ce1e7d26e35b.ngrok.io/bot/ask-restaurants?closer=city");
        messageService.addButton("No percurso", "http://ce1e7d26e35b.ngrok.io/bot/ask-restaurants?closer=route");

        return messageService.getMessageGroup();
    }

    public MessageGroup showRestaurants(String closer) {

        List<Location> locations = centralService.findRestaurants(closer);

        messageService.startMessage();
        messageService.addText("Estes são os postos e restaurantes que recomendamos no seu trecho:");
        messageService.addButton(locations.get(0).getName(), "http://ce1e7d26e35b.ngrok.io/bot/show-restaurant-details?id=" + locations.get(0).getId());
        messageService.addButton(locations.get(1).getName(), "http://ce1e7d26e35b.ngrok.io/bot/show-restaurant-details?id=" + locations.get(0).getId());
        messageService.addButton(locations.get(2).getName(), "http://ce1e7d26e35b.ngrok.io/bot/show-restaurant-details?id=" + locations.get(0).getId());

        return messageService.getMessageGroup();
    }


    public MessageGroup showRestaurantDetails(String id) {

        Restaurant restaurant = centralService.findRestaurant(id);

        messageService.startMessage();
        messageService.addText("[" + restaurant.getName() + "] " + restaurant.getAddress() + " " + restaurant.getRanking() + "/5");
        messageService.addButton("Ir para Google Maps", "https://www.google.com.br/maps/@" + restaurant.getLatitude() + "," + restaurant.getLongitude(), true);
        messageService.addButton("Compartilhar a localização", "http://ce1e7d26e35b.ngrok.io/bot/ask-information-ok");

        return messageService.getMessageGroup();
    }

    public MessageGroup askInformationOk() {

        messageService.startMessage();
        messageService.addText("As informações foram úteis?");
        messageService.addButton("Sim :)", "http://ce1e7d26e35b.ngrok.io/bot/ask-to-collaborate");
        messageService.addButton("Não :(", "http://ce1e7d26e35b.ngrok.io/bot/ask-to-collaborate");

        return messageService.getMessageGroup();
    }

    public MessageGroup askToColaboratte() {

        messageService.startMessage();
        messageService.addText("Sabia que colaborando com informações novas sobre o trecho você ganha pontos que valem prêmios?");
        messageService.addButton("Colaborar", "http://ce1e7d26e35b.ngrok.io/bot/ending");
        messageService.addButton("Encerrar", "http://ce1e7d26e35b.ngrok.io/bot/ending");

        return messageService.getMessageGroup();
    }

    public MessageGroup ending() {
        messageService.startMessage();
        return messageService.onlyQuickText("O InfoTrecho deseja uma boa viagem! Caso precise falar com a gente é só dizer um oi!");
    }

}
