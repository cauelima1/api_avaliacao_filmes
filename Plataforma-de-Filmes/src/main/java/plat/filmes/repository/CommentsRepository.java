package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plat.filmes.model.submodel.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
