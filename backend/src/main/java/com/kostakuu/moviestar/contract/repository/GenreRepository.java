package com.kostakuu.moviestar.contract.repository;

import com.kostakuu.moviestar.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findGenresByDeleted(boolean isDeleted);
    Genre findGenreByIdAndDeleted(int id, boolean isDeleted);
}
