package plat.filmes.service;

import org.hibernate.sql.Delete;
import plat.filmes.model.submodel.Comments;
import plat.filmes.model.DTO.CommentsDTO;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    void delete (Long id);

    Optional<Comments> findById(String id);

    List<Comments> findAll();

    Comments create(CommentsDTO commentsDTO);


}
