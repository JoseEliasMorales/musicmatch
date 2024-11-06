package com.aluracursos.musicmatch.repository;

import com.aluracursos.musicmatch.models.Artista;
import com.aluracursos.musicmatch.models.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query(value = "SELECT * FROM artistas", nativeQuery = true)
    List<Artista> buscarTodosLosArtistas();

    Optional<Artista> findByNombreContainsIgnoreCase(String nombreArtista);

    @Query("SELECT c FROM Artista a JOIN a.canciones c WHERE c.titulo ILIKE %:nombreCancion%")
    Optional<Cancion> findByTituloContainsIgnoreCase(String nombreCancion);

}
