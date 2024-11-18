package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.OmdbDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.submodel.Comments;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.MovieService;
import plat.filmes.service.OMDBService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingsServiceImpl ratingService;

    @Autowired
    private OMDBService omdbService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie create(Movie movie) {
        return null;
    }

    public Movie consultMovie(String name) {

        OmdbDTO omdbDTO = omdbService.consultMovie(name);
        if(!movieRepository.existsById(omdbDTO.getImdbID())) {
            Movie movie = new Movie();
            movie.setImdbUser(0d);
            movie.setGenre(omdbDTO.getGenre());
            movie.setImdbID(omdbDTO.getImdbID());
            movie.setTitle(omdbDTO.getTitle());
            movie.setImdbRating(omdbDTO.getImdbRating());

            return movieRepository.save(movie);
        } else {
            return movieRepository.findById(omdbDTO.getImdbID()).get();
        }
    }

    public void deleteCommentFromMovie(String imdbId, Long commentId){
        Optional<Movie> movie = movieRepository.findById(imdbId);
        if (movie.isPresent()){
            List<Comments> commentsList = commentsRepository.findAll().stream().filter(c-> !c.getId().equals(commentId)).toList();
            Movie movieWhtioutComment = null;
            movieWhtioutComment.setComments(commentsList);
            movieRepository.save(movieWhtioutComment);
        }
    }


}