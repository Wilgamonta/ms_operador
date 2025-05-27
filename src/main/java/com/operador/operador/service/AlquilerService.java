package com.operador.operador.service;

import com.operador.operador.client.PeliculaClient;
import com.operador.operador.dto.AlquilerDTO;
import com.operador.operador.dto.Pelicula;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j
public class AlquilerService {

    private static final Logger log = LoggerFactory.getLogger(AlquilerService.class);
    private final PeliculaClient peliculaClient;
    private final WebClient webClient;


    public AlquilerService(PeliculaClient peliculaClient, WebClient.Builder webClientBuilder) {
        this.peliculaClient = peliculaClient;
        this.webClient = webClientBuilder.baseUrl("http://buscador").build();
    }

    public Mono<Pelicula> obtenerPelicula(Long id) {
        log.info("EUREKA obtenerPelicula {}", id);
        return webClient.get()
                .uri("/api/peliculas/{id}", id)
                .retrieve()
                .bodyToMono(Pelicula.class);
    }

    public Map<String, Object> alquilar(Long peliculaId, String nombreCliente) {
        Map<String, Object> pelicula = peliculaClient.obtenerPeliculaPorId(peliculaId);

        if (pelicula == null) {
            throw new RuntimeException("Película no encontrada");
        }

        AlquilerDTO alquiler = new AlquilerDTO();
        alquiler.setPeliculaId(peliculaId);
        alquiler.setNombreCliente(nombreCliente);
        alquiler.setFechaAlquiler(LocalDateTime.now());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("alquiler", alquiler);
        respuesta.put("pelicula", pelicula);

        return respuesta;
    }
}
