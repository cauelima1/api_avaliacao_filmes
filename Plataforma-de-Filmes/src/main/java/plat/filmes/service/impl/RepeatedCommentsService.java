package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.RepeatedCommentDTO;
import plat.filmes.model.submodel.Comments;
import plat.filmes.model.submodel.RepeatedComment;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.RepeatedCommentRepository;

import java.util.List;

@Service
public class RepeatedCommentsService {

    @Autowired
    private RepeatedCommentRepository repeatedCommentRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    public Comments markAsRepeated(RepeatedCommentDTO repeatedCommentDTO){
            if (commentsRepository.existsById(repeatedCommentDTO.getCommentId())){
                RepeatedComment repeatedComment = new RepeatedComment();
                repeatedComment.setId(repeatedComment.getId());
                repeatedComment.setCommentId(repeatedCommentDTO.getCommentId());
                repeatedComment.setRepeated(repeatedCommentDTO.isRepeated());
                repeatedCommentRepository.save(repeatedComment);
                Comments commentMarked = commentsRepository.findById(repeatedCommentDTO.getCommentId()).get();
                commentMarked.setRepeatedComment(repeatedComment);
                return commentsRepository.save(commentMarked);
            } else {
                throw new RuntimeException("Comment not found.");
            }
        }
}
