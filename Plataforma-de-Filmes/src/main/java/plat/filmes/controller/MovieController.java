package plat.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Rating;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.impl.MovieServiceImpl;

import java.util.List;
import java.util.Optional;

/*
Classe criada para verificar os filmes diretamente do metodo GET no Insomnia
 */
@RestController
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    private UserRepository userRepository;

    private final MovieRepository movieRepository;
    private final MovieServiceImpl movieService;

    public MovieController(MovieRepository movieRepository, MovieServiceImpl movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Movie> ConsultMovie(@PathVariable String name){
        return ResponseEntity.ok(movieService.consultMovie(name));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> showAllMovies(){
        return ResponseEntity.ok(movieRepository.findAll());
    }

//    @PostMapping("/movies/comment")
//    public ResponseEntity<Movie> CommentMovie(@RequestBody CommentsMovieDTO movieDTO){
//      Movie commentedMovie = movieService.create(movieDTO);
//      return ResponseEntity.ok(commentedMovie);
//    }


    @PutMapping("/rating/{imdbId}")
    public ResponseEntity <Rating> MovieRating(@PathVariable("imdbId") String imdbId
                                            , @RequestBody RatingMovieDTO ratingMovieDTO) throws Exception {
        Rating ratedMovie = movieService.ratingMovieByUser(imdbId, ratingMovieDTO);
        return ResponseEntity.ok(ratedMovie);
    }
}
