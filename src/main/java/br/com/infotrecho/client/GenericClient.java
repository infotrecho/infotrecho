package br.com.infotrecho.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public abstract class GenericClient {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    @Autowired
    private OkHttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;
}
