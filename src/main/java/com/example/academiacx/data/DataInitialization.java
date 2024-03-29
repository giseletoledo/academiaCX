package com.example.academiacx.data;

import com.example.academiacx.models.*;
import com.example.academiacx.services.inter.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataInitialization implements CommandLineRunner {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private FranchiseService franchiseService;

    @Autowired
    private StreamingService streamingService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            // Mock dos dados para cadastrar filmes
            StudioModel studio = new StudioModel("Marvel Studios","EUA");
            GenreModel genre = new GenreModel("Ação");

            // Salvar genero e studio, por causa dos relacionamentos de tabela
            studioService.create(studio);
            genreService.create(genre);

            // Criando alguns diretores
            DirectorModel director1 = new DirectorModel("Joe Russo");
            directorService.create(director1);

            DirectorModel director2 = new DirectorModel("Anthony Russo");
            directorService.create(director2);

            DirectorModel director3 = new DirectorModel("Jon Watts");
            directorService.create(director3);

            DirectorModel director4 = new DirectorModel("John Fraveau");
            directorService.create(director4);

            // Adicionando atores
            List<ActorModel> actors = new ArrayList<>();
            for (String actorName : List.of("Samuel L. Jackson", "Brie Larson", "Chris Evans")) {
                try {
                    ActorModel actor = new ActorModel(actorName);
                    actor = actorService.create(actor); // Salvar o ator e obter o objeto salvo
                    actors.add(actor);
                } catch (Exception e) {
                    // Lidar com exceções ao salvar atores
                    System.err.println("Erro ao salvar ator: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // Adicionando diretores
            List<DirectorModel> directors = new ArrayList<>();
            for (String directorName : List.of("Joe Russo", "Anthony Russo", "Jon Watts", "John Fraveau")) {
                try {
                    DirectorModel director = new DirectorModel(directorName);
                    director = directorService.create(director); // Salvar o diretor e obter o objeto salvo
                    directors.add(director);
                } catch (Exception e) {
                    // Lidar com exceções ao salvar diretores
                    System.err.println("Erro ao salvar diretor: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            // Criando os filmes
            MovieModel movieAvengers = new MovieModel("Vingadores: Ultimato", genre, studio);
            movieAvengers.setDirectors(directors);
            movieAvengers.setActors(actors);

            MovieModel movieSpiderman = new MovieModel("Homem-Aranha: Sem Volta Para Casa", genre, studio);
            movieSpiderman.setDirectors(directors);
            movieSpiderman.setActors(actors);

            StreamingModel disneyPlus = streamingService.create(new StreamingModel("DisneyPlus", "http://www.disneyplus.com.br"));
            movieAvengers.setStreaming(disneyPlus);
            movieSpiderman.setStreaming(disneyPlus);

            // Criando a franquia
            FranchiseModel franchise = new FranchiseModel("Marvel Cinematic Universe", genre, studio, List.of(movieAvengers, movieSpiderman));
            movieSpiderman.setFranchise(franchise);
            movieAvengers.setFranchise(franchise);

            // Salvando a franquia com o Service
            franchiseService.create(franchise);

            // Salvando os filmes no repositório
            movieService.create(movieAvengers);
            movieService.create(movieSpiderman);

            Long userId = 1L;
            Long movieId = 1L;

            // Construir o objeto JSON com o ID do filme
            String requestBody = "{\"movieIds\":" + movieId + "}";

            // Criar um cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Construir a requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/users/" + userId + "/bookmark"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Enviar a requisição e obter a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            if (response.statusCode() == 200) {
                System.out.println("Movie added to favorites successfully.");
            } else {
                System.out.println("Failed to add movie to favorites: " + response.body());
            }

            // Listando todos os filmes
            System.out.println("All Movies:");
            movieService.list().forEach(movie -> {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Genre: " + movie.getGenre().getGenreName());
                System.out.println("Atores: " + movie.getActors());
                System.out.println("Directors: " + movie.getDirectors());
                System.out.println("Studio: " + movie.getStudio().getName());
                System.out.println("Franchise: " + movie.getFranchise().getName());
                System.out.println("Streaming: " + movie.getStreaming().getName());
                System.out.println("-----------------------------");
            });


        } catch (Exception e) {
            // Lidar com exceções gerais
            System.err.println("Erro ao inicializar dados: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
