package plat.filmes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings", uniqueConstraints = {@UniqueConstraint(columnNames = {"user","imdbId"})})
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String imdbId;

    @Column
    private String user;

    @Column
    private double ratingByUser;

}
