package com.kostakuu.moviestar.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private int duration;

    @Column(name = "number_of_views")
    private int numberOfViews;

    @ManyToMany
    @JoinTable(name = "movies_genres", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "movie")
    private List<Projection> projections;

    @Column(name = "deleted")
    private boolean deleted;

    public Movie() {
    }

    public Movie(int id, String name, int duration, int numberOfViews, List<Genre> genres, List<Projection> projections, boolean deleted) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.numberOfViews = numberOfViews;
        this.genres = genres;
        this.projections = projections;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public void setProjections(List<Projection> projections) {
        this.projections = projections;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
