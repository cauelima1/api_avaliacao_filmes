package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.RatingMovieDTO;
import plat.filmes.model.Rating;
import plat.filmes.model.User;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.RatingService;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Optional<Rating> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Rating> findAll() {
        return List.of();
    }

    @Override
    public Rating create(Rating rating) {
        return null;
    }

    public Rating ratingMovieByUser(String imdbId, RatingMovieDTO ratingMovieDTO) {
        try{
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
            }
        } catch (Exception e) {
            throw new RuntimeException("Login or imdbID is wrong.");
        }
        return null;
    }

    //todo - fazer a logica de calcular a media na classe Movie
    //TODO - ACRESCENTAR LOGICA ATE 20 PONTOS PARA VIRAR BASICO E FILTRAR PARA
    public void incrementPointsUser(User user, String login) {
        user = (User) userRepository.findByLogin(login);
        user.setPoints(user.getPoints() + 1);
        userRepository.save(user);
    }
}
