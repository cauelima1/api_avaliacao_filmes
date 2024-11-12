package plat.filmes.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany
    List<ReplyComments> replyComments;
}
