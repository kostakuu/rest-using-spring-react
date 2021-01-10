package com.kostakuu.moviestar.contract.repository;

import com.kostakuu.moviestar.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findMoviesByDeleted(boolean isDeleted);
    Movie findMovieByIdAndDeleted(int id, boolean isDeleted);
}
