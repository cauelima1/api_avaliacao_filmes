package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.OmdbDTO;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.User;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.MovieService;
import plat.filmes.service.OMDBService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private OMDBService omdbService;

    @Autowired
    private UserRepository userRepository;

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        return this.findAll();
    }

    @Override
    public Movie create(Movie movie) {
        return null;
    }

    public Movie consultMovie(String name) {
        OmdbDTO omdbDTO = omdbService.consultMovie(name);
        Movie movie = new Movie();
        movie.setGenre(omdbDTO.getGenre());
        movie.setImdbID(omdbDTO.getImdbID());
        movie.setTitle(omdbDTO.getTitle());
        movie.setImdbRating(omdbDTO.getImdbRating());
        return movieRepository.save(movie);
    }

    public Movie ratingMovieByUser(RatingMovieDTO ratingMovieDTO){
        Movie ratedMovie = new Movie();
        ratedMovie.setTitle(ratedMovie.getTitle());
        ratedMovie.setGenre(ratedMovie.getGenre());
        ratedMovie.setImdbRating(ratedMovie.getImdbRating());

//        reviews.setUserID(user.getId());
//        reviews.setImdbID(ratingMovieDTO.getImdbID());
//        reviews.setAvarageRating(ratingMovieDTO.getRatingMovieByUser());
//        user.setUserPoints(+1);
//        System.out.println(reviews);
        return movieRepository.save(ratedMovie);
    }
}
