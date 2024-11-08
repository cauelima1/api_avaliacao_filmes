package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plat.filmes.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {


}
