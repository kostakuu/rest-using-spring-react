package com.kostakuu.moviestar.service;

import com.kostakuu.moviestar.contract.repository.MovieRepository;
import com.kostakuu.moviestar.contract.service.MovieServiceInterface;
import com.kostakuu.moviestar.dto.MovieDto;
import com.kostakuu.moviestar.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieServiceInterface {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> getAll() {
        return movieRepository.findMoviesByDeleted(false).stream().map(MovieDto::new).collect(Collectors.toList());
    }

    @Override
    public MovieDto findById(int id) {
        Movie movie = movieRepository.findMovieByIdAndDeleted(id, false);

        if (movie == null) return null;

        movie.setNumberOfViews(movie.getNumberOfViews() + 1);
        return new MovieDto(movieRepository.save(movie));
    }

    @Override
    public MovieDto save(Movie movie) {
        return new MovieDto(movieRepository.save(movie));
    }

    @Override
    public MovieDto deleteById(int id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) return null;

        movie.setDeleted(true);
        return new MovieDto(movieRepository.save(movie));
    }
}
