package plat.filmes.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.Comments;
import plat.filmes.model.DTO.CommentsDTO;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Ratings;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.service.impl.CommentsServiceImpl;
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
    private final CommentsServiceImpl commentsService;

    public MovieController(RatingsServiceImpl ratingService, RatingRepository ratingRepository, MovieRepository movieRepository, MovieServiceImpl movieService, CommentsServiceImpl commentsService) {
        this.ratingService = ratingService;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.commentsService = commentsService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> showAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Movie> consultMovie(@PathVariable String name){
        return ResponseEntity.ok(movieService.consultMovie(name));
    }

    @GetMapping("/rating")
    public ResponseEntity<List<Ratings>> showAllRatings () {
        return ResponseEntity.ok(ratingService.findAll())  ;
    }


    @PutMapping("/rating/{imdbId}")
    public ResponseEntity <Ratings> covieRating(@PathVariable("imdbId") String imdbId
                                            , @RequestBody RatingMovieDTO ratingMovieDTO) {
        Ratings ratedMovie = ratingService.ratingMovieByUser(imdbId, ratingMovieDTO);
        return ResponseEntity.ok(ratedMovie);
    }


    @PostMapping("/comments")
    public ResponseEntity<Comments> createMovieComments(@RequestBody CommentsDTO commentsDTO) {
        Comments comments = commentsService.create(commentsDTO);
        return ResponseEntity.ok(comments);
    }


    @GetMapping("/comments")
    public ResponseEntity<List<Comments>> showAllComments() {
        return ResponseEntity.ok(commentsService.findAll());
    }

}
