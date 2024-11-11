package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.Comments;
import plat.filmes.model.DTO.CommentsDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.User;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.CommentsService;
import plat.filmes.service.MovieService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Optional<Comments> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public Comments create(CommentsDTO commentsDTO) {
        if(movieRepository.existsById(commentsDTO.getImdId())) {
            Comments comments = new Comments();
            User user = new User();
            String login = userService.recoverUserLogin(Optional.of(user));
            user = (User) userRepository.findByLogin(login);

            comments.setId(comments.getId());
            comments.setImdbId(commentsDTO.getImdId());
            comments.setComment(commentsDTO.getComments());
            comments.setUser(login);
            comments.setTittle(searchTittle(commentsDTO.getImdId()));

            commentsRepository.save(comments);
            userService.incrementPointsUser(user);

//            movieRepository.findById(comments.getImdbId()).ifPresent(m->
//            {
//                m.setComments(List.of(comments));
//                movieRepository.save(m);
//            });

            return comments;
        } else {
            throw new RuntimeException("ImdbId not found.");
        }
    }

    public String searchTittle(String imdbId){
       Movie movie = movieRepository.findById(imdbId).get();
       String tittle = movie.getTitle();
        return tittle;
    }

}


