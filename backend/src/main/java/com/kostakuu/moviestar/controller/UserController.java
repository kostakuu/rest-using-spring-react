package com.kostakuu.moviestar.controller;

import com.kostakuu.moviestar.contract.service.UserServiceInterface;
import com.kostakuu.moviestar.dto.UserDto;
import com.kostakuu.moviestar.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOne(@PathVariable int id) {
        UserDto user = userService.findById(id);
        HttpStatus httpStatus = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, httpStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }
}
