package plat.filmes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import plat.filmes.model.submodel.Comments;

import java.util.*;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
public class Movie {

    @Id
    private String imdbID;
    private String Title;
    private String Genre;
    private String imdbRating;
    private Double imdbUser;

    @OneToMany
    private List<Comments> comments;

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Movie(List<Comments> comments) {
        this.comments = comments;
    }





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


    public void setImdbUser(Double imdbUser) {
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
