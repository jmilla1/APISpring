package com.iplacex.discografia.Artista;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;



@Document(collection = "artistas")
public class Artista {
    @Id
    public String _id;
    public String nombre;
    public List<String> estilos;
    public int anioFundacion;
    public boolean estaActivo;
}
