package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.ReplyCommentsDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.ReplyComments;
import plat.filmes.model.User;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.ReplyCommentsRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.ReplyCommentsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyCommentsServiceImpl implements ReplyCommentsService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReplyCommentsRepository replyCommentsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    public Movie replyComments (String imdbId, ReplyCommentsDTO replyCommentsDTO){
        if(movieRepository.existsById(imdbId)){
            if(commentsRepository.existsById(replyCommentsDTO.getCommentId())){
                User user = new User();
                String login = userService.recoverUserLogin(Optional.of(user));
                user = (User) userRepository.findByLogin(login);

                ReplyComments replyComments = new ReplyComments();
                replyComments.setLogin(login);
                replyComments.setId(replyComments.getId());
                replyComments.setReplyComments(replyCommentsDTO.getReplyComments());
                replyComments.setCommentsId(replyCommentsDTO.getCommentId());

                replyCommentsRepository.save(replyComments);
                userService.incrementPointsUser(user);

                commentsRepository.findById(replyCommentsDTO.getCommentId()).ifPresent(
                        comments -> {
                            List<ReplyComments> existingRelpy = comments.getReplyComments();
                            if(existingRelpy == null) {
                                existingRelpy = new ArrayList<>();
                            }
                            existingRelpy.add(replyComments);
                            comments.setReplyComments(existingRelpy);
                            commentsRepository.save(comments);
                        }
                );
                return movieRepository.findById(imdbId).get();
            } else {
                throw new RuntimeException("No exists comments for this movie.");
            }
        } else {
            throw new RuntimeException("Movie does not exists.");
        }
    }

}
