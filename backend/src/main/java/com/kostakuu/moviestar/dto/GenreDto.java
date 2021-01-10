package com.kostakuu.moviestar.dto;

import com.kostakuu.moviestar.entity.Genre;

public class GenreDto {
    public int id;
    public String name;

    public GenreDto() {
    }

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
