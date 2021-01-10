package com.kostakuu.moviestar.dto;

import com.kostakuu.moviestar.entity.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDto {
    public int id;
    public String name;
    public int duration;
    public int numberOfViews;
    public List<GenreDto> genres;

    public MovieDto() {
    }

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.duration = movie.getDuration();
        this.numberOfViews = movie.getNumberOfViews();

        if (movie.getGenres() != null)
            this.genres = movie.getGenres().stream().map(GenreDto::new).collect(Collectors.toList());
    }
}
