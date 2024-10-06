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
    @JoinColumn(name = "media_id")
    @JsonBackReference
    private Media media;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    @Column(name = "listened")
    private Boolean listened;

    @Column(name = "is_recommended")
    private Boolean isRecommended = false;


    public Recommendation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getListened() {
        return listened;
    }

    public void setListened(Boolean listened) {
        this.listened = listened;
    }

    public Boolean getRecommended() {
        return isRecommended;
    }

    public void setRecommended(Boolean recommended) {
        isRecommended = recommended;
    }
}
