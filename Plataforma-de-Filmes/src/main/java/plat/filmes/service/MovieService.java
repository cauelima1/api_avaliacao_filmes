package plat.filmes.service;

import plat.filmes.model.DTO.CommentsMovieDTO;
import plat.filmes.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<Movie> findById(String id);

    List<Movie> findAll();

    Movie create(CommentsMovieDTO movieDTO);
}
