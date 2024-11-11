package plat.filmes.service;

import org.springframework.stereotype.Service;
import plat.filmes.model.Ratings;

import java.util.List;
import java.util.Optional;

@Service
public interface RatingService {

    Optional<Ratings> findById(String id);

    List<Ratings> findAll();

    Ratings create(Ratings ratings);
}
