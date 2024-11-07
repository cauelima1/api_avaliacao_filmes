package plat.filmes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratting")
public class Rating {

    @Id
    private Long id;
    private String imdbID;
    private double ratingByUser;

    @ManyToOne
    @JoinColumn(name="movie")
    private Movie movie;

}
