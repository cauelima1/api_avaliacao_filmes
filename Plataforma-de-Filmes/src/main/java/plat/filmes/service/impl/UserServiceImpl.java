package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.UserDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Perfil;
import plat.filmes.model.Ratings;
import plat.filmes.model.User;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RatingRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.OMDBService;
import plat.filmes.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OMDBService omdbService;


    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public UserServiceImpl(MovieRepository movieRepository, UserRepository usuarioRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = usuarioRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.getReferenceById(id));
    }

    @Override
    public User create(UserDTO userDTO) {
        User usuarioCadastrado = new User();
        usuarioCadastrado.setId(usuarioCadastrado.getId());
        usuarioCadastrado.setLogin(userDTO.getLogin());
        usuarioCadastrado.setPassword(userDTO.getPassword());
        usuarioCadastrado.setPerfil(usuarioCadastrado.getPerfil());
        usuarioCadastrado.setPoints(0);
        return userRepository.save(usuarioCadastrado);
    }

    public String recoverUserLogin(Optional<User> user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String name;

        if (principal instanceof UserDetails){
            name = ((UserDetails)principal).getUsername();
        } else {
            name = principal.toString();
        }
        return name;
    }

    public void pointsValidation (User user) {
        if (!user.getPerfil().equals(Perfil.MODERADOR)) {
            if (user.getPoints() >= 20 && user.getPoints() < 100) {
                user.setPerfil(Perfil.BASICO);
            }
            if (user.getPoints() >= 100 && user.getPoints() < 1000) {
                user.setPerfil(Perfil.AVANCADO);
            }
            if (user.getPoints() >= 1000){
                user.setPerfil(Perfil.MODERADOR);
            }

       }
    }

    public void AvarageRate(String imdbId){

        List<Ratings> ratings = ratingRepository.findByImdbId(imdbId);

        double media = ratings.stream().mapToDouble(Ratings::getRatingByUser).average().orElse(0);
        movieRepository.findById(imdbId).ifPresent(movie -> {
            movie.setImdbUser(media);
            movieRepository.save(movie);
        });

    }

    public void incrementPointsUser(User user) {
        user.setPoints(user.getPoints() + 1);
        userRepository.save(user);
        pointsValidation(user);
    }
}