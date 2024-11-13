package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.submodel.Comments;
import plat.filmes.model.DTO.CommentsDTO;
import plat.filmes.model.Movie;
import plat.filmes.model.User;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.MovieRepository;
import plat.filmes.repository.UserRepository;
import plat.filmes.service.CommentsService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            comments.setLogin(login);
            comments.setTittle(searchTittle(commentsDTO.getImdId()));
            comments.setQuotes(null);

            commentsRepository.save(comments);
            userService.incrementPointsUser(user);

            movieRepository.findById(comments.getImdbId()).ifPresent(m->
            {
                List<Comments> existingComments = m.getComments();
                if(existingComments ==null) {
                    existingComments = new ArrayList<>();
                }
                existingComments.add(comments);
                m.setComments(existingComments);
                movieRepository.save(m);
            });

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


    public List<Comments> duplicatedComments (){
        List<Comments> allComments = commentsRepository.findAll();
        if(!allComments.isEmpty()){
            Map<String, Long> commentCountMap = allComments.stream()
                    .collect(Collectors.groupingBy(Comments::getComment, Collectors.counting()));

            List<Comments> duplicatedComments = allComments.stream()
                    .filter(c->commentCountMap.get(c.getComment())>1)
                    .distinct()
                    .collect(Collectors.toList());

            return duplicatedComments;
        }
        return null;
    }

}


