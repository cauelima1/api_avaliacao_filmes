package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.submodel.Ratings;

import java.util.List;


public interface RatingRepository extends JpaRepository<Ratings, Long> {

    public List<Ratings> findByImdbId (String imdbId);

    public boolean existsByImdbIdAndLogin(String imdbId, String login);

    public boolean existsByImdbId(String imdbId);

}
