package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plat.filmes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
