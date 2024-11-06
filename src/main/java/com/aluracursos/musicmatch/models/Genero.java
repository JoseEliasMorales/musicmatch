package com.aluracursos.musicmatch.models;

public enum Genero {
    POP("Pop"),
    ROCK("Rock"),
    HIP_HOP("Hip hop"),
    RAP("Rap"),
    ELECTRONICA("Electronica"),
    REGGAETON("Reggaeton"),
    JAZZ("Jazz"),
    BLUES("Blues"),
    CLASICA("Clasica"),
    HEAVY_METAL("Heavy metal"),
    PUNK("Punk"),
    DISCO("Disco"),
    SKA("Ska"),
    K_POP("K-Pop"),
    TRAP("Trap"),
    SALSA("Salsa"),
    BACHATA("Bachata"),
    REGGAE("Reggae"),
    CUMBIA("Cumbia");

    private String generoMusical;

    Genero(String generoMusical){
        this.generoMusical=generoMusical;
    }

    public static Genero fromString(String texto){
        for(Genero genero : Genero.values()){
            if(genero.generoMusical.equalsIgnoreCase(texto)){
                return genero;
            }
        }

        throw new IllegalArgumentException("Genero " + texto + " no encontrado");
    }
}
