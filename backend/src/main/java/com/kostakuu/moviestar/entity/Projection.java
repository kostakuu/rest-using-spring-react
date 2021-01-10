package com.kostakuu.moviestar.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "projection")
public class Projection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projection_id", unique = true)
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "movie", referencedColumnName = "movie_id")
    private Movie movie;

    @Column(name = "room")
    private String room;

    @Column(name = "price")
    private double price;

    @Column(name = "deleted")
    private boolean deleted;

    public Projection() {
    }

    public Projection(int id, Date date, Movie movie, String room, double price, boolean deleted) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.room = room;
        this.price = price;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
