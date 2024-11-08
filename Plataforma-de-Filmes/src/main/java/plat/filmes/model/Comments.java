package plat.filmes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    private String imdbID;
    private Long userID;
    private String comment;

    @ManyToOne
    @JoinColumn(name="movie")
    private Movie movie;

}
