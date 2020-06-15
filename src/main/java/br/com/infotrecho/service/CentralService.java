package br.com.infotrecho.service;

import br.com.infotrecho.model.Location;
import br.com.infotrecho.model.Restaurant;
import br.com.infotrecho.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CentralService {

    public void saveUser(User user) {
        // save user in central
    }

    public List<Location> findRestaurants(String closer) {
        //find restaurant in central
        return Arrays.asList(
                Location.builder().id("1").name("Restaurante do Nardão").build(),
                Location.builder().id("2").name("Restaurante Graal").build(),
                Location.builder().id("3").name("Posto Frango Frito").build());
    }

    public Restaurant findRestaurant(String id) {
        if (id.equalsIgnoreCase("2")) {
            return Restaurant.builder().name("Restaurante Graal").address("Rua Manacas Almeida, 12 - Centro - Araraquara, SP").latitude("12.4564841").longitude("02.545645645").ranking(2).build();
        }
        if (id.equalsIgnoreCase("3")) {
            return Restaurant.builder().name("Posto Frango Frito").address("Rua 7, 88 - Centro - Araraquara, SP").latitude("12.4564841").longitude("02.545645645").ranking(2).build();
        }

        return Restaurant.builder().name("Restaurante do Nardão").address("Rua Abreu, 633 - Centro - Araraquara, SP").latitude("12.4564841").longitude("02.545645645").ranking(2).build();
    }
}
