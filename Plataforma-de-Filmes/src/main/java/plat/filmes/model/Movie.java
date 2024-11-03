package plat.filmes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.NonNull;


@Entity
public class Movie {

    @Id
    private String imdbID;

    @NonNull
    private String tittle;

    @NonNull
    private String genre;
    private String imdbRating;

    private String comments;

    @Override
    public String toString() {
        return "Movie{" +
                "imdbID='" + imdbID + '\'' +
                ", tittle='" + tittle + '\'' +
                ", genre='" + genre + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public @NonNull String getTittle() {
        return tittle;
    }

    public void setTittle(@NonNull String tittle) {
        this.tittle = tittle;
    }

    public @NonNull String getGenre() {
        return genre;
    }

    public void setGenre(@NonNull String genre) {
        this.genre = genre;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }



}
