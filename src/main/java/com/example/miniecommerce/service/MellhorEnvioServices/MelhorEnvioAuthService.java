package com.example.miniecommerce.service.MellhorEnvioServices;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Service
public class MelhorEnvioAuthService {

    private String accessToken;
    private long expiryTime; // timestamp em millis
    private final String clientId = "6859"; // seu client_id do sandbox
    private final String clientSecret = "jTCANtqNAqjPJpioSjGON9xkFqRgMycixupjIREu";

    public String getToken() {
        if (accessToken == null || System.currentTimeMillis() > expiryTime) {
            fetchToken();
        }
        return accessToken;
    }

    private void fetchToken() {
        String url = "https://sandbox.melhorenvio.com.br/oauth/token";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String body = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        this.accessToken = (String) responseBody.get("access_token");
        int expiresIn = (int) responseBody.get("expires_in");
        this.expiryTime = System.currentTimeMillis() + (expiresIn * 1000L) - 60000; // renova 1 min antes
    }
}
