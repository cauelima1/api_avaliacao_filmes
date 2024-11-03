package plat.filmes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plat.filmes.model.Movie;
import plat.filmes.repository.MovieRepository;
import plat.filmes.service.impl.UserService;

/*
Classe criada para verificar os filmes diretamente do metodo GET no Insomnia
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final UserService usuarioService;

    public MovieController(MovieRepository movieRepository, UserService usuarioService) {
        this.movieRepository = movieRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Movie> ConsultMovie(@PathVariable String name){
        return ResponseEntity.ok(usuarioService.consultMovie(name));
    }

}
