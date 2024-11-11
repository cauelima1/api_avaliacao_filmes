package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.OmdbDTO;
import plat.filmes.model.Movie;
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
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

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
            movie.setImdbUser(0D);
            movie.setGenre(omdbDTO.getGenre());
            movie.setImdbID(omdbDTO.getImdbID());
            movie.setTitle(omdbDTO.getTitle());
            movie.setImdbRating(omdbDTO.getImdbRating());
            return movieRepository.save(movie);
        } else {
            if (movieRepository.existsById(omdbDTO.getImdbID()))
                return movieRepository.findById(omdbDTO.getImdbID()).get();
        }
        return null;
    }


}