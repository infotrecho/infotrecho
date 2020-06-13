package br.com.infotrecho.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ButtonsResponse {
    private List<Messages> messages;

    @Data
    @Builder
    public static class Messages {
        private Attachment attachment;

        @Data
        @Builder
        public static class Attachment {
            private final String type = "template";
            private Payload payload;

            @Data
            @Builder
            public static class Payload {

                @JsonProperty("template_type")
                private final String templateType = "button";
                private String text;
                private List<Button> buttons;

                @Data
                @Builder
                public static class Button {
                    private final String type = "web_url";
                    private String url;
                    private String title;
                }
            }
        }
    }
}
