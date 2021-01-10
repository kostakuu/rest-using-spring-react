package com.kostakuu.moviestar.dto;

public class TokenDto {
    public String accessToken;
    public int expiresIn;

    public TokenDto(String accessToken, int expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
