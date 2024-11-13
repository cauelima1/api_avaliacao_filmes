package plat.filmes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plat.filmes.model.DTO.QuoteDTO;
import plat.filmes.model.User;
import plat.filmes.model.submodel.Comments;
import plat.filmes.model.submodel.Quote;
import plat.filmes.repository.CommentsRepository;
import plat.filmes.repository.QuoteRepository;
import plat.filmes.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public Comments quoteOfComments (QuoteDTO quoteDTO){
        if(commentsRepository.existsById(quoteDTO.getIdComment())){
            User user = new User();
            String login = userService.recoverUserLogin(Optional.of(user));

            Quote newQuote = new Quote();
            newQuote.setId(newQuote.getId());
            newQuote.setIdComment(quoteDTO.getIdComment());
            newQuote.setIfLike(quoteDTO.isIfLike());
            newQuote.setLogin(login);
            quoteRepository.save(newQuote);
            commentsRepository.findById(quoteDTO.getIdComment()).ifPresent(q->
            {
                List<Quote> quotes = q.getQuotes();
                if(quotes == null){
                    quotes = new ArrayList<>();
                }
                quotes.add(newQuote);
                q.setQuotes(quotes);
                commentsRepository.save(q);
            });
            return commentsRepository.findById(quoteDTO.getIdComment()).get();
        }
        throw new RuntimeException("Comment not found to be quoted.");
    }

    public List<Comments> quoteComments(){
        List<Comments> commentsQuoted = commentsRepository.findAll().stream().filter(c->
                c.getQuotes() != null && !c.getQuotes().isEmpty())
                .collect(Collectors.toList());

        return commentsQuoted;
    }

}
