package com.iplacex.discografia.Disco;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.iplacex.discografia.Artista.IArtistaRepository;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {
    private final IDiscoRepository discoRepository;
    private final IArtistaRepository artistaRepository;

    public DiscoController(IDiscoRepository discoRepository, IArtistaRepository artistaRepository) {
        this.discoRepository = discoRepository;
        this.artistaRepository = artistaRepository;
    }

    // Crear un nuevo disco
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepository.existsById(disco.idArtista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(discoRepository.save(disco));
    }

    // Obtener todos los discos
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        return ResponseEntity.ok(discoRepository.findAll());
    }

    // Obtener un disco por ID
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleGetDiscoRequest(@PathVariable String id) {
        Optional<Disco> disco = discoRepository.findById(id);
        if (disco.isPresent()) {
            return ResponseEntity.ok(disco.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
        }
    }

    // Obtener todos los discos de un artista por ID de artista
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleGetDiscosByArtistaRequest(@PathVariable String id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return ResponseEntity.ok(discos);
    }

    // Eliminar un disco por ID
    @DeleteMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleDeleteDiscoRequest(@PathVariable String id) {
        if (discoRepository.existsById(id)) {
            discoRepository.deleteById(id);
            return ResponseEntity.ok("Disco eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
        }
    }
}
