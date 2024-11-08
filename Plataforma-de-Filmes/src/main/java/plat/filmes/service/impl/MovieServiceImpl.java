package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.OmdbDTO;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Rating;
import plat.filmes.model.User;
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

    public Rating ratingMovieByUser(String imdbId, RatingMovieDTO ratingMovieDTO) {
        User user = new User();
        Rating rating = new Rating();
        String login = userService.recoverUserLogin(Optional.of(user));

        if (!login.isEmpty() && movieRepository.existsById(imdbId)) {
            incrementPointsUser(user, login);

            rating.setId(rating.getId());
            rating.setUser(login);
            rating.setImdbId(imdbId);
            rating.setRatingByUser(ratingMovieDTO.getRatingMovieByUser());

            return ratingRepository.save(rating);

        } else {
            throw new RuntimeException("Login or imdbID is wrong.");
        }
    }

    //todo - fazer a logica de calcular a media na classe Movie
    //TODO - ACRESCENTAR LOGICA ATE 20 PONTOS PARA VIRAR BASICO E FILTRAR PARA
    public void incrementPointsUser(User user, String login) {
        user = (User) userRepository.findByLogin(login);
        user.setPoints(user.getPoints() + 1);
        userRepository.save(user);
    }
}