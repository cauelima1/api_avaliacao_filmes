package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.submodel.RepeatedComment;

public interface RepeatedCommentRepository extends JpaRepository<RepeatedComment, Long> {
}
