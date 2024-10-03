package se.salts.recommendationsservice.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "media")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 100)
    private String title;


    @Column(name = "media_type", length = 100)
    private String mediaType;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;


    @Column(name = "release_date",length = 100)
    private LocalDate releaseDate;

    @Column(name = "url", length = 100)
    private String url;


    @Column(name = "duration",length = 100)
    private String duration;


    @ManyToOne (optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Recommendation> recommendations = new HashSet<>();

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlaybackHistory> playBackHistories = new HashSet<>();

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TopMedia> topMedia = new HashSet<>();

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Rating> ratings = new HashSet<>();



    public Media() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Set<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public Set<PlaybackHistory> getPlayBackHistories() {
        return playBackHistories;
    }

    public void setPlayBackHistories(Set<PlaybackHistory> playBackHistories) {
        this.playBackHistories = playBackHistories;
    }

    public Set<TopMedia> getTopMedia() {
        return topMedia;
    }

    public void setTopMedia(Set<TopMedia> topMedia) {
        this.topMedia = topMedia;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
