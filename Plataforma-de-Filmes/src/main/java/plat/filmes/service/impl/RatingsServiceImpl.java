package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Perfil;
import plat.filmes.model.Ratings;
import plat.filmes.model.User;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.RatingService;

import java.util.List;
import java.util.Optional;

@Service
public class RatingsServiceImpl implements RatingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Optional<Ratings> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Ratings> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Ratings create(Ratings ratings) {
        return null;
    }

    public Ratings ratingMovieByUser(String imdbId, RatingMovieDTO ratingMovieDTO) {
        if (movieRepository.existsById(imdbId)) {
            if (ratingMovieDTO.getRatingMovieByUser() <= 10 && ratingMovieDTO.getRatingMovieByUser() >= 0) {
                User user = new User();
                String login = userService.recoverUserLogin(Optional.of(user));

                if (!ratingRepository.existsByImdbIdAndUser(imdbId, login)) {
                    Movie movieToRate = movieRepository.findById(imdbId).get();
                    movieToRate.setImdbUser(ratingMovieDTO.getRatingMovieByUser());
                    user = (User) userRepository.findByLogin(login);

                    userService.incrementPointsUser(user);
                    Ratings ratings = new Ratings();
                    ratings.setId(ratings.getId());
                    ratings.setUser(login);
                    ratings.setImdbId(imdbId);
                    ratings.setRatingByUser(ratingMovieDTO.getRatingMovieByUser());
                    ratingRepository.save(ratings);
                    userService.AvarageRate(imdbId);
                    return ratings;

                } else {
                    throw new RuntimeException("This movie already has been rated by this user.");
                }
            } else {
                throw new RuntimeException("The rating must be between  0 and 10.");
            }
        } else {
            throw new RuntimeException("This movie does not exists in repository.");
        }
    }


}




