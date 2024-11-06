package principal;

import com.aluracursos.musicmatch.models.Artista;
import com.aluracursos.musicmatch.models.Cancion;
import com.aluracursos.musicmatch.models.Genero;
import com.aluracursos.musicmatch.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    Artista artista = new Artista();
    private ArtistaRepository repository;
    List<Artista> artistas = new ArrayList<>();
    Optional<Artista> artistaEncontrado;
    String artistaBuscado;
    public Principal(ArtistaRepository repository) {
        this.repository=repository;
    }

    public void muestraElMenu(){
        System.out.println("""
                Por favor, elige que deseas hacer:
                
                1 - Agregar artista
                2 - Agregar cancion a artista
                3 - Buscar Artista
                4 - Buscar por cancion
                
                
                0 - Salir
                """);

        var opcion = teclado.nextInt();
        teclado.nextLine();
        
        switch (opcion){
            case 1:
                agregarArtista();
                break;
            case 2:
                buscarArtistaParaAgregarCancion();
                agregarCancionAlArtista(artistaEncontrado.get());
                break;
            case 3:
                buscarArtista();
                break;
            case 4:
                buscarCancion();
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private void agregarArtista() {
        System.out.println("Por favor dime el nombre del artista: ");

        var nombreArtista = teclado.nextLine();
        artista.setNombre(nombreArtista);



        System.out.println("Por favor, indica el genero al que pertenece: ");
        var genero = teclado.nextLine();
        Genero corroborarGenero = Genero.fromString(genero);
        artista.setGenero(corroborarGenero);


        System.out.println("Por favor indica su pais de origen: ");
        var pais = teclado.nextLine();
        artista.setPais(pais);


        repository.save(artista);

        System.out.println("""
                Deseas agregar alguna cancion a este artista?
                
                1 - Si
                2 - No
                
                """);

        var opcionAgregar = teclado.nextInt();
        teclado.nextLine();

        switch (opcionAgregar){
            case 1:
                agregarCancionAlArtista(artista);
                break;
            case 2:
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }

    }


    private void buscarArtistaParaAgregarCancion(){
        artistas = repository.buscarTodosLosArtistas();

        System.out.println("Por favor elige un artista de la lista: ");

        artistas.forEach(e-> System.out.println(e.getNombre()));

        artistaBuscado = teclado.nextLine();

        artistaEncontrado = repository.findByNombreContainsIgnoreCase(artistaBuscado);

    }

    private void agregarCancionAlArtista(Artista artista){

        System.out.println("Por favor, ingresa el nombre de la cancion.");

        var cancionAAgregar = teclado.nextLine();

        System.out.println("Por favor, dime a que album pertenece. Si no pertenece a ninguno pon 'Sencillo'");

        var albumAlQuePertenece = teclado.nextLine();

        Cancion cancion = new Cancion(cancionAAgregar, albumAlQuePertenece, artista);
        List<Cancion> canciones = new ArrayList<>();
        canciones.add(cancion);
        if (artista.getCanciones() == null) {
            artista.setCantidadCanciones( canciones.size());
        } else {
            artista.setCantidadCanciones(artista.getCanciones().size() + canciones.size());
        }

        artista.setCanciones(canciones);


        repository.save(artista);
    }

    private void buscarArtista(){
        System.out.println("Por favor, dime el nombre del artista que buscas");

        var artistaABuscar = teclado.nextLine();

        artistaEncontrado = repository.findByNombreContainsIgnoreCase(artistaABuscar);

        System.out.println("\nDetalles del artista:\n");
        System.out.println("Artista: " + artistaEncontrado.get().getNombre() + "\n" +
                            "Genero: " + artistaEncontrado.get().getGenero() + "\n" +
                            "Pais de origen: " + artistaEncontrado.get().getPais() + "\n" +
                            "Cantidad de canciones: " + artistaEncontrado.get().getCantidadCanciones() + "\n" +
                            "Canciones: " + artistaEncontrado.get().getCanciones() + "\n"
                            );
    }


    private void buscarCancion(){
        System.out.println("Por favor, escribe el nombre de la cancion: ");

        var cancionBuscada = teclado.nextLine();

        var cancionEncontrada = repository.findByTituloContainsIgnoreCase(cancionBuscada);

        System.out.println("\n Detalles de la cancion: \n");
        System.out.println("Titulo: " + cancionEncontrada.get().getTitulo() + "\n" +
                            "Artista: " + cancionEncontrada.get().getArtista().getNombre() + "\n" +
                            "Album: " + cancionEncontrada.get().getAlbum() + "\n");
    }

}
