package com.operador.operador.controller;

import com.operador.operador.service.AlquilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;


@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

//    @PostMapping
//    public ResponseEntity<Map<String, Object>> alquilar(
//            @RequestParam Long peliculaId,
//            @RequestParam String cliente
//    ) {
//        Map<String, Object> resultado = alquilerService.alquilar(peliculaId, cliente);
//        return ResponseEntity.ok(resultado);
//    }

    @PostMapping
    public Mono<ResponseEntity<String>> alquilarPelicula(
            @RequestParam Long peliculaId,
            @RequestParam String cliente) {

        return alquilerService.obtenerPelicula(peliculaId)
                .map(pelicula -> {
                    // Aquí podrías agregar lógica para crear un "alquiler"
                    String mensaje = "Cliente " + cliente + " alquiló la película: " + pelicula.getTitulo();
                    return ResponseEntity.ok(mensaje);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}