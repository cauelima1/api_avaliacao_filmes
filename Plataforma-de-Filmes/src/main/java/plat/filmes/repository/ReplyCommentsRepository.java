package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.submodel.ReplyComments;


public interface ReplyCommentsRepository extends JpaRepository<ReplyComments, Long> {
}
