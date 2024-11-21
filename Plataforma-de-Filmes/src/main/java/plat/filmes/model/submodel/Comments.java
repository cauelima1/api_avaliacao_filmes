package plat.filmes.model.submodel;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plat.filmes.model.Movie;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String comment;
    @Column
    private String imdbId;
    @Column
    private String tittle;
    @Column
    private String login;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id", referencedColumnName = "imdbID", nullable = false)
    private Movie movie;

    @OneToOne
    private RepeatedComment repeatedComment;

    @OneToMany
    private List<Quote> quotes;

    @OneToMany
    private List<ReplyComments> replyComments;

}
