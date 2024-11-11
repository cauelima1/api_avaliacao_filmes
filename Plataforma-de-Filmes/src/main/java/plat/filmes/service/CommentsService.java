package plat.filmes.service;

import plat.filmes.model.Comments;
import plat.filmes.model.DTO.CommentsDTO;

import java.util.List;
import java.util.Optional;

public interface CommentsService {


    Optional<Comments> findById(String id);

    List<Comments> findAll();

    Comments create(CommentsDTO commentsDTO);
}
