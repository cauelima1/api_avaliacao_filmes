package plat.filmes.service.impl;

import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.CommentsMovieDTO;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.MovieService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserServiceImpl userServiceImpl;

    public MovieServiceImpl(UserRepository userRepository, MovieRepository movieRepository, UserServiceImpl userServiceImpl) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        return List.of();
    }

    @Override
    public Movie create(CommentsMovieDTO movieDTO) {
        Movie commentedMovie = new Movie();
        commentedMovie.setTittle(commentedMovie.getTittle());
        commentedMovie.setImdbID(commentedMovie.getImdbID());
        commentedMovie.setImdbRating(commentedMovie.getImdbRating());
        commentedMovie.setComments(movieDTO.getComments());
        return movieRepository.save(commentedMovie);
    }



    public Movie ratingMovieByUser(RatingMovieDTO ratingMovieDTO){
        Movie ratedMovie = new Movie();
        ratedMovie.setTittle(ratedMovie.getTittle());
        ratedMovie.setImdbID(ratedMovie.getImdbID());
        ratedMovie.setImdbRating(ratedMovie.getImdbRating());
        ratedMovie.setUserRating(ratingMovieDTO.getRatingMovieByUser());
        return movieRepository.save(ratedMovie);
    }
}
