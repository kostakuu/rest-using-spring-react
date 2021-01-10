package com.kostakuu.moviestar.service;

import com.kostakuu.moviestar.contract.repository.ProjectionRepository;
import com.kostakuu.moviestar.contract.service.ProjectionServiceInterface;
import com.kostakuu.moviestar.dto.ProjectionDto;
import com.kostakuu.moviestar.entity.Projection;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectionService implements ProjectionServiceInterface {
    private final ProjectionRepository projectionRepository;

    public ProjectionService(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    @Override
    public List<ProjectionDto> getAll() {
        return projectionRepository.findProjectionsByDeleted(false).stream().map(ProjectionDto::new).collect(Collectors.toList());
    }

    @Override
    public ProjectionDto findById(int id) {
        Projection projection = projectionRepository.findProjectionByIdAndDeleted(id, false);
        return projection != null ? new ProjectionDto(projection) : null;
    }

    @Override
    public ProjectionDto save(Projection projection) {
        return new ProjectionDto(projectionRepository.save(projection));
    }

    @Override
    public ProjectionDto deleteById(int id) {
        Projection projection = projectionRepository.findById(id).orElse(null);

        if (projection == null) return null;

        projection.setDeleted(true);
        return new ProjectionDto(projectionRepository.save(projection));
    }
}
