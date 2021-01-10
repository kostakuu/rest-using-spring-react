package com.kostakuu.moviestar.controller;

import com.kostakuu.moviestar.contract.service.ProjectionServiceInterface;
import com.kostakuu.moviestar.dto.ProjectionDto;
import com.kostakuu.moviestar.dto.UserDto;
import com.kostakuu.moviestar.entity.Projection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/projection")
public class ProjectionController {
    private final ProjectionServiceInterface projectionService;

    public ProjectionController(ProjectionServiceInterface projectionService) {
        this.projectionService = projectionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(projectionService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable int id) {
        ProjectionDto projection = projectionService.findById(id);
        HttpStatus httpStatus = projection != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(projection, httpStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Projection projection) {
        return new ResponseEntity<>(projectionService.save(projection), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Projection projection) {
        return new ResponseEntity<>(projectionService.save(projection), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(projectionService.deleteById(id), HttpStatus.OK);
    }
}
