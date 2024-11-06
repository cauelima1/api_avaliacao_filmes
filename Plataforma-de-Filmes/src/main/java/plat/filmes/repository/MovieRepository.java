package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.Movie;



public interface MovieRepository extends JpaRepository<Movie, String> {

}
