package plat.filmes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import plat.filmes.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Movie {

    @Id
    private String imdbID;
    private String Title;
    private String Genre;
    private String imdbRating;
    private double imdbUser;

    //    @OneToMany(mappedBy = "movie")
//    private List<Comments> comments = new ArrayList<>();

    public Movie(String imdbID, String title, String genre, String imdbRating, double imdbUser) {
        this.imdbID = imdbID;
        Title = title;
        Genre = genre;
        this.imdbRating = imdbRating;
        this.imdbUser = imdbUser;
    }

    public Movie(double imdbUser) {
        this.imdbUser = imdbUser;
    }

    public double getImdbUser() {
        return imdbUser;
    }

    public void setImdbUser(double imdbUser) {
        this.imdbUser = imdbUser;
    }


    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Movie (){
    }


}
