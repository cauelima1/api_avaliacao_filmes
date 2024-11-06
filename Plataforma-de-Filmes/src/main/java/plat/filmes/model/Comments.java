package plat.filmes.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_comments")
public class Comments {

    @Id
    private String imdbID;
    private Long userID;

    @ManyToMany
    private List<Comments> commentsList;

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public Comments(String imdbID, Long userID, List<Comments> commentsList) {
        this.imdbID = imdbID;
        this.userID = userID;
        this.commentsList = commentsList;
    }
}
