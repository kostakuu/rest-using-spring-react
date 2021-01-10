package com.kostakuu.moviestar.service;

import com.kostakuu.moviestar.contract.repository.UserRepository;
import com.kostakuu.moviestar.contract.service.UserServiceInterface;
import com.kostakuu.moviestar.dto.UserDto;
import com.kostakuu.moviestar.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findUsersByDeleted(false).stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(int id) {
        User user = userRepository.findUserByIdAndDeleted(id, false);
        return user != null ? new UserDto(user) : null;
    }

    @Override
    public UserDto save(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        return new UserDto(userRepository.save(user));
    }

    @Override
    public UserDto deleteById(int id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) return null;

        user.setDeleted(true);
        return new UserDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsernameAndDeleted(username, false);
    }
}
