package com.aluracursos.musicmatch.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String pais;
    private Integer cantidadCanciones;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cancion> canciones;


    public Artista(){}




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }



    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public Integer getCantidadCanciones() {
        return cantidadCanciones;
    }

    public void setCantidadCanciones(Integer cantidadCanciones) {
        this.cantidadCanciones = cantidadCanciones;
    }

    @Override
    public String toString() {
        return "Artista=" + nombre + '\'' +
                ", genero=" + genero +
                ", pais=" + pais + '\'' +
                ", canciones=" + canciones;
    }

}
