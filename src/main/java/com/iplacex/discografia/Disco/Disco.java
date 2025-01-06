package com.iplacex.discografia.Disco;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

@Document(collection = "discos")
public class Disco {
    @Id
    public String _id;
    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
}
