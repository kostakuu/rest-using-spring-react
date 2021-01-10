package com.kostakuu.moviestar.contract.repository;

import com.kostakuu.moviestar.entity.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Integer> {
    List<Projection> findProjectionsByDeleted(boolean isDeleted);
    Projection findProjectionByIdAndDeleted(int id, boolean isDeleted);
}
