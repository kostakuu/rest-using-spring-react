package com.kostakuu.moviestar.contract.service;

import com.kostakuu.moviestar.dto.UserDto;
import com.kostakuu.moviestar.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends BaseServiceInterface<User, UserDto>, UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
