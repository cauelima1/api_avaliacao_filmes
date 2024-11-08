package plat.filmes.service;

import org.springframework.stereotype.Service;
import plat.filmes.model.Movie;
import plat.filmes.model.Rating;

import java.util.List;
import java.util.Optional;

@Service
public interface RatingService {

    Optional<Rating> findById(String id);

    List<Rating> findAll();

    Rating create(Rating rating);
}
