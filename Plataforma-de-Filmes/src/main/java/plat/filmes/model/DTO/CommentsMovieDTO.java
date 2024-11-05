package plat.filmes.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plat.filmes.repository.UserRepository;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsMovieDTO {



    private List<String> comments;  //como fazer um map ou algo similar
    private String ImdbID;
}
