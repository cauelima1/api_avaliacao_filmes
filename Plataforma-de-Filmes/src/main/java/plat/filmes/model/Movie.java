package plat.filmes.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Movie {

    @Id
    private String imdbID;
    private String Title;
    private String Genre;
    private String imdbRating;

    @OneToMany(mappedBy = "movie")
    private List<Comments> comments;

    @OneToMany(mappedBy = "movie")
    private List<Rating> rattings;


    public List<Comments> getComments() {
        return comments;
    }

    public Movie(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Rating> getRattings() {
        return rattings;
    }

    public void setRattings(List<Rating> rattings) {
        this.rattings = rattings;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
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


    public Movie(String imdbID, String Title, String genre, String imdbRating) {
        this.imdbID = imdbID;
        this.Title = Title;
        this.Genre = genre;
        this.imdbRating = imdbRating;
    }


    public Movie (){
    }

}
