package com.aluracursos.musicmatch.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "artista_id") // Define la columna de clave foránea en la tabla canciones
    private Artista artista;
    private String nombreArtista;
    private String album;

    public Cancion(){}

    public Cancion(String titulo) {
        this.titulo = titulo;
    }



    public Cancion(String cancionAAgregar, String albumAlQuePertenece, Artista e) {
        this.titulo = cancionAAgregar;
        this.album = albumAlQuePertenece;
        this.artista = e;
        this.nombreArtista = e.getNombre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }


    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "{\n" +
                "titulo: " + titulo + '\'' +
                ", album: " + album + '\'' +
                "\n}";
    }
}
