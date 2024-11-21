package plat.filmes.service;

import org.hibernate.sql.Delete;
import plat.filmes.model.Movie;
import plat.filmes.model.submodel.Comments;

import java.util.List;
import java.util.Optional;

public interface MovieService {


    Optional<Movie> findById(String id);

    List<Movie> findAll();

    Movie create(Movie movie);
}
