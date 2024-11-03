package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.UserDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.Perfil;
import plat.filmes.model.User;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.OMDBService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements plat.filmes.service.UserService {

    @Autowired
    private OMDBService omdbService;

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public UserService(MovieRepository movieRepository, UserRepository usuarioRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = usuarioRepository;
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
        usuarioCadastrado.setName(userDTO.getName());
        usuarioCadastrado.setPassword(userDTO.getPassword());
        usuarioCadastrado.setPerfil(Perfil.LEITOR);
        return userRepository.save(usuarioCadastrado);
    }

    public Movie consultMovie (String name) {
        Movie movie = omdbService.consultMovie(name);
        movieRepository.save(movie);
        return movie;
    }

}
