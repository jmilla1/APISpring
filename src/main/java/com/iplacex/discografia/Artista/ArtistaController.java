package com.iplacex.discografia.Artista;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {
    private final IArtistaRepository repository;

    public ArtistaController(IArtistaRepository repository) {
        this.repository = repository;
    }

    // Insertar un artista
    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(artista));
    }

    // Obtener todos los artistas
    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> handleGetArtistasRequest() {
        return ResponseEntity.ok(repository.findAll());
    }

    // Obtener un artista por ID
    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleGetArtistaRequest(@PathVariable String id) {
        Optional<Artista> artista = repository.findById(id);
        if (artista.isPresent()) {
            return ResponseEntity.ok(artista.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
    }

    // Actualizar un artista por ID
    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleUpdateArtistaRequest(@PathVariable String id, @RequestBody Artista artista) {
        if (repository.existsById(id)) {
            artista._id = id; // Asegurarse de que el ID del artista coincide con el de la solicitud
            return ResponseEntity.ok(repository.save(artista));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado para actualizar");
        }
    }

    // Eliminar un artista por ID
    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handleDeleteArtistaRequest(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Artista eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado para eliminar");
        }
    }
}
