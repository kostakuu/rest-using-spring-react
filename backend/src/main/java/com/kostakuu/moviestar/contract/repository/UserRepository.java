package com.kostakuu.moviestar.contract.repository;

import com.kostakuu.moviestar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUsersByDeleted(boolean isDeleted);
    User findUserByIdAndDeleted(int id, boolean isDeleted);
    User findUserByUsernameAndDeleted(String username, boolean isDeleted);
}
