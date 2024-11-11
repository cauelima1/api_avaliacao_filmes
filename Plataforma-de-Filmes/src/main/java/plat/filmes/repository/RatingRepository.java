package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.Ratings;

import java.util.List;


public interface RatingRepository extends JpaRepository<Ratings, Long> {

    public List<Ratings> findByImdbId (String imdbId);

    public boolean existsByImdbIdAndUser(String imdbId, String user);

    public boolean existsByImdbId(String imdbId);

}
