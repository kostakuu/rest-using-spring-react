package com.kostakuu.moviestar.dto;

import com.kostakuu.moviestar.entity.Projection;

import java.util.Date;

public class ProjectionDto {
    public int id;
    public Date date;
    public MovieDto movie;
    public String room;
    public double price;

    public ProjectionDto() {
    }

    public ProjectionDto(Projection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.movie = new MovieDto(projection.getMovie());
        this.room = projection.getRoom();
        this.price = projection.getPrice();
    }
}
