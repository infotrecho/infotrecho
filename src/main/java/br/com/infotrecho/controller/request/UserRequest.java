package br.com.infotrecho.controller.request;

import lombok.Data;

@Data
public class UserRequest {

    private Route route;
    private User user;

    @Data
    public static class Route {
        private String start;
        private String end;
    }

    @Data
    public static class User {
        private String messengerId;
        private String firstName;
        private String lastName;
    }

}