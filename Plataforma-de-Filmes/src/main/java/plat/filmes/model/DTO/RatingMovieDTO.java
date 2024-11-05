package plat.filmes.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingMovieDTO {

    private String ImdbID;
    private List<String> ratingMovieByUser; //como fazer um map ou algo similar
}
