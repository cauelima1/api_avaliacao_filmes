package plat.filmes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    private String imdbID;
    private String tittle;
    private String genre;
    private String imdbRating;
    private String comments;


    @Override
    public String toString() {
        return "Movie{" +
                "imdbID='" + imdbID + '\'' +
                ", tittle='" + tittle + '\'' +
                ", genre='" + genre + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
