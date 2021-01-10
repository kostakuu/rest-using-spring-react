package com.kostakuu.moviestar.dto;

import com.kostakuu.moviestar.entity.User;
import com.kostakuu.moviestar.type.Gender;

public class UserDto {
    public int id;
    public String username;
    public String password;
    public String fullName;
    public Gender gender;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.gender = user.getGender();
    }
}
