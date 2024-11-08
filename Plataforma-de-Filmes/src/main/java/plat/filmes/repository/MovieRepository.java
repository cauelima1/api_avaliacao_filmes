package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plat.filmes.model.Movie;



public interface MovieRepository extends JpaRepository<Movie, String> {

}
