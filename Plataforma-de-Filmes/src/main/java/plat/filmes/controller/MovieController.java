package plat.filmes.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plat.filmes.model.DTO.*;
import plat.filmes.model.submodel.Comments;
import plat.filmes.model.Movie;
import plat.filmes.model.submodel.Ratings;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.QuoteRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.service.CommentsService;
import plat.filmes.service.impl.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final RepeatedCommentsService repeatedCommentsService;
    private final CommentsRepository commentsRepository;
    private final ReplyCommentsServiceImpl replyCommentsService;
    private final RatingsServiceImpl ratingService;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final MovieServiceImpl movieService;
    private final CommentsService commentsService;
    private final QuoteServiceImpl quoteService;
    private final QuoteRepository quoteRepository;

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

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<List<Comments>> deleteComment(@PathVariable("id") Long id){
        commentsService.delete((id));
        return ResponseEntity.ok(commentsRepository.findAll());
    }

    @PostMapping("/comments/{imdbId}")
    public ResponseEntity <Movie> replayComments(@PathVariable("imdbId") String imdbId
                                                ,@RequestBody ReplyCommentsDTO replyCommentsDTO) {
        Movie movie = replyCommentsService.replyComments(imdbId, replyCommentsDTO);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/comments/like")
    public ResponseEntity<Comments> quoteComments (@RequestBody QuoteDTO quoteDTO){
        Comments commentQuoted = quoteService.quoteOfComments(quoteDTO);
        return ResponseEntity.ok(commentQuoted);
    }

    @GetMapping("/comments/like")
    public ResponseEntity<List<Comments>> showAllQquotes (){
        return ResponseEntity.ok(quoteService.quoteComments());
    }

    @GetMapping("/comments/duplicated")
    public ResponseEntity<List<Comments>> showDuplicatedComments (){
        List<Comments> duplicated = commentsService.duplicatedComments();
        return ResponseEntity.ok(duplicated);
    }

    @PostMapping("/comments/duplicated")
    public ResponseEntity<Comments> repeatedComment (@RequestBody RepeatedCommentDTO repeatedCommentDTO) {
        Comments repeatedComment = repeatedCommentsService.markAsRepeated(repeatedCommentDTO);

    return ResponseEntity.ok(repeatedComment);
    }


}
