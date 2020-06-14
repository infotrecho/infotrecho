package br.com.infotrecho.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageGroup {

    private List<Messages> messages;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Messages {

        private Attachment attachment;
        private String text;

        @JsonProperty("quick_replies")
        private List<QuickReply> quickReplies;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Attachment {

            private AttachmentType type;
            private Payload payload;

            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            @JsonInclude(JsonInclude.Include.NON_NULL)
            public static class Payload {

                @JsonProperty("template_type")
                private PayloadType templateType;
                private String text;
                private List<Button> buttons;
                private Elements elements;

                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                @JsonInclude(JsonInclude.Include.NON_NULL)
                public static class Elements {

                    private MediaType type;
                    private String url;
                    private List<Button> buttons;
                }

                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                @JsonInclude(JsonInclude.Include.NON_NULL)
                public static class Button {

                    private ButtonType type;
                    private String url;
                    private String title;
                }

                public void addButton(Button button) {
                    if (buttons == null) {
                        buttons = new ArrayList<>();
                    }
                    buttons.add(button);
                }
            }
        }

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class QuickReply {

            private String title;
            private String url;
            private ButtonType buttonType;

        }
    }
}
