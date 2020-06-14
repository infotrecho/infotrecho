package br.com.infotrecho.config;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpClientFactory {
    @Bean
    public OkHttpClient OkHttpClientFactory() {
        return new OkHttpClient();
    }
}
