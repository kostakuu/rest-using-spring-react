package com.kostakuu.moviestar.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

    @Column(name = "deleted")
    private boolean deleted;

    public Genre() {
    }

    public Genre(int id, String name, List<Movie> movies, boolean deleted) {
        this.id = id;
        this.name = name;
        this.movies = movies;
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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
