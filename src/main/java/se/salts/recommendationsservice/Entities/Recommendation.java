package se.salts.recommendationsservice.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "recommendation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "media_id")
    @JsonBackReference
    private Media media;


    @Column(name = "listened")
    private Boolean listened;

    public Recommendation() {
    }

    public Recommendation(Long id, User user, Media media, Boolean listened) {
        this.id = id;
        this.user = user;
        this.media = media;
        this.listened = listened;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Boolean getListened() {
        return listened;
    }

    public void setListened(Boolean listened) {
        this.listened = listened;
    }
}
