package com.kostakuu.moviestar.controller;

import com.kostakuu.moviestar.contract.service.GenreServiceInterface;
import com.kostakuu.moviestar.dto.GenreDto;
import com.kostakuu.moviestar.dto.MovieDto;
import com.kostakuu.moviestar.entity.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/genre")
public class GenreController {
    private final GenreServiceInterface genreService;

    public GenreController(GenreServiceInterface genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable int id) {
        GenreDto genre = genreService.findById(id);
        HttpStatus httpStatus = genre != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(genre, httpStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.save(genre), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.save(genre), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(genreService.deleteById(id), HttpStatus.OK);
    }
}
