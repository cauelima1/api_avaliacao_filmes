package plat.filmes.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Ratings;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.service.impl.MovieServiceImpl;
import plat.filmes.service.impl.RatingsServiceImpl;

import java.util.List;

/*
Classe criada para verificar os filmes diretamente do metodo GET no Insomnia
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final RatingsServiceImpl ratingService;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final MovieServiceImpl movieService;

    public MovieController(RatingsServiceImpl ratingService, RatingRepository ratingRepository, MovieRepository movieRepository, MovieServiceImpl movieService) {
        this.ratingService = ratingService;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> showAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Movie> ConsultMovie(@PathVariable String name){
        return ResponseEntity.ok(movieService.consultMovie(name));
    }


    @GetMapping("/rating")
    public ResponseEntity<List<Ratings>> showAllRatings () {
        return ResponseEntity.ok(ratingService.findAll())  ;
    }


    @PutMapping("/rating/{imdbId}")
    public ResponseEntity <Ratings> MovieRating(@PathVariable("imdbId") String imdbId
                                            , @RequestBody RatingMovieDTO ratingMovieDTO) {
        Ratings ratedMovie = ratingService.ratingMovieByUser(imdbId, ratingMovieDTO);
        return ResponseEntity.ok(ratedMovie);
    }




}
