package br.com.infotrecho.utils;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class Properties {

    private final ResourceBundle resourceBundle;

    public static final String WELCOME = "welcome";
    public static final String _1_TEXT = "1.text";

    public static final String _2_TEXT = "2.text";
    public static final String _2_OPTION_GOTO_3 = "2.option.goto.3";
    public static final String _2_OPTION_GOTO_4 = "2.option.goto.4";
    public static final String _2_OPTION_GOTO_5 = "2.option.goto.5";

    public static final String _3_TEXT = "3.text";

    public static final String _4_TEXT = "4.text";

    public static final String _5_TEXT = "5.text";
    public static final String _5_OPTION_GOTO_6 = "5.option.goto.6";
    public static final String _5_OPTION_GOTO_7 = "5.option.goto.7";
    public static final String _5_OPTION_GOTO_8 = "5.option.goto.8";

    public static final String _6_OPTION_GOTO_10 = "6.option.goto.10";
    public static final String _6_OPTION_GOTO_11 = "6.option.goto.11";

    public static final String _7_OPTION_GOTO_20 = "7.option.goto.20";
    public static final String _7_OPTION_GOTO_21 = "7.option.goto.21";

    public Properties() {
        this.resourceBundle = ResourceBundle.getBundle("messages");
    }

    public String getText(String key) {
        return resourceBundle.getString(key);
    }
}
