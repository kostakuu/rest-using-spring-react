package com.kostakuu.moviestar.controller;

import com.kostakuu.moviestar.contract.service.MovieServiceInterface;
import com.kostakuu.moviestar.dto.MovieDto;
import com.kostakuu.moviestar.dto.ProjectionDto;
import com.kostakuu.moviestar.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movie")
public class MovieController {
    private final MovieServiceInterface movieService;

    public MovieController(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable int id) {
        MovieDto movie = movieService.findById(id);
        HttpStatus httpStatus = movie != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(movie, httpStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(movieService.deleteById(id), HttpStatus.OK);
    }
}
