package plat.filmes.model;

import jakarta.persistence.*;

import java.util.List;


@Entity (name = "tb_movies")
public class Movie {

    @Id
    private String imdbID;
    private String Title;
    private String Genre;
    private String imdbRating;

    @ManyToMany
    private List<Comments> commentsList;


    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public Movie(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
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
