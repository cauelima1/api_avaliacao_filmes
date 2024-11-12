package plat.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plat.filmes.model.ReplyComments;


public interface ReplyCommentsRepository extends JpaRepository<ReplyComments, Long> {
}
