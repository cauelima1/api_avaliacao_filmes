package plat.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.CommentsMovieDTO;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.repository.MovieRepository;
import plat.filmes.service.MovieService;
import plat.filmes.service.impl.MovieServiceImpl;
import plat.filmes.service.impl.UserServiceImpl;

/*
Classe criada para verificar os filmes diretamente do metodo GET no Insomnia
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    private final MovieServiceImpl movieService;
    private final MovieRepository movieRepository;
    private final UserServiceImpl userService;

    public MovieController(MovieServiceImpl movieService, MovieRepository movieRepository, UserServiceImpl usuarioService) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.userService = usuarioService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Movie> ConsultMovie(@PathVariable String name){
        return ResponseEntity.ok(userService.consultMovie(name));
    }


    //postar comentarios - TODO -> Verificar Perfil
    @PostMapping("/movies/comment")
    public ResponseEntity<Movie> CommentMovie(@RequestBody CommentsMovieDTO movieDTO){
      Movie commentedMovie = movieService.create(movieDTO);
      return ResponseEntity.ok(commentedMovie);
    }

    //postar avaliacao - TODO -> como verificar perfil
    @PostMapping("movies/rating")
    public ResponseEntity<Movie> MovieRating(@RequestBody RatingMovieDTO ratingMovieDTO){
        Movie ratedMovie = movieService.ratingMovieByUser(ratingMovieDTO);
        return ResponseEntity.ok(ratedMovie);
    }



}
