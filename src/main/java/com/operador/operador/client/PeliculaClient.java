package com.operador.operador.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class PeliculaClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/api/peliculas";

    public PeliculaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> obtenerPeliculaPorId(Long id) {
        try {
            return restTemplate.getForObject(baseUrl + "/" + id, Map.class);
        } catch (Exception e) {
            return null;
        }
    }
}
