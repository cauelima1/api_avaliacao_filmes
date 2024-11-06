package plat.filmes.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plat.filmes.model.Movie;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingMovieDTO {

    private String ImdbID;
    private double ratingMovieByUser;
}
