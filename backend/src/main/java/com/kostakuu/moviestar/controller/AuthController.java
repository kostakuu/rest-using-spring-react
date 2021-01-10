package com.kostakuu.moviestar.controller;

import com.kostakuu.moviestar.dto.TokenDto;
import com.kostakuu.moviestar.entity.User;
import com.kostakuu.moviestar.security.token.TokenHelper;
import com.kostakuu.moviestar.security.request.JwtAuthenticationRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenHelper tokenHelper;

    public AuthController(AuthenticationManager authenticationManager, TokenHelper tokenHelper) {
        this.authenticationManager = authenticationManager;
        this.tokenHelper = tokenHelper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User authUser = (User) authentication.getPrincipal();
        String jws = tokenHelper.generateToken(authUser.getUsername());

        int expiresIn = tokenHelper.getExpiredIn();

        return ResponseEntity.ok(new TokenDto(jws, expiresIn));
    }
}
