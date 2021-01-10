package com.kostakuu.moviestar.service;

import com.kostakuu.moviestar.contract.repository.GenreRepository;
import com.kostakuu.moviestar.contract.service.GenreServiceInterface;
import com.kostakuu.moviestar.dto.GenreDto;
import com.kostakuu.moviestar.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService implements GenreServiceInterface {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> getAll() {
        return genreRepository.findGenresByDeleted(false).stream().map(GenreDto::new).collect(Collectors.toList());
    }

    @Override
    public GenreDto findById(int id) {
        Genre genre = genreRepository.findGenreByIdAndDeleted(id, false);
        return genre != null ? new GenreDto(genre) : null;
    }

    @Override
    public GenreDto save(Genre genre) {
        return new GenreDto(genreRepository.save(genre));
    }

    @Override
    public GenreDto deleteById(int id) {
        Genre genre = genreRepository.findById(id).orElse(null);

        if (genre == null) return null;

        genre.setDeleted(true);
        return new GenreDto(genreRepository.save(genre));
    }
}
