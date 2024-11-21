package plat.filmes.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import plat.filmes.model.submodel.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Transactional
    void deleteById (Long id);

}
