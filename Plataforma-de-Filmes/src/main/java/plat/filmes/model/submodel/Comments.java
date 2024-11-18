package plat.filmes.model.submodel;



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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String comment;
    @Column
    private String imdbId;
    @Column
    private String tittle;
    @Column
    private String login;

    @OneToOne
    private RepeatedComment repeatedComment;

    @OneToMany
    private List<Quote> quotes;

    @OneToMany
    private List<ReplyComments> replyComments;
}
