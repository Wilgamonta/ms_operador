package com.operador.operador.service;

import com.operador.operador.client.PeliculaClient;
import com.operador.operador.dto.AlquilerDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AlquilerService {

    private final PeliculaClient peliculaClient;

    public AlquilerService(PeliculaClient peliculaClient) {
        this.peliculaClient = peliculaClient;
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
