package plat.filmes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "tb_movies")
public class Movie {

    @Id
    private String imdbID;
    private String tittle;
    private String genre;
    private String imdbRating;

    private List<String> comments;

    private List<String> userRating;



//    @OneToMany(cascade = CascadeType.MERGE)
//    private List<String> comments;
//
//    @OneToMany(cascade = CascadeType.MERGE)
//    private List<Integer> userRating;



}
