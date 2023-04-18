package com.lhind.repository;

import com.lhind.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRole(String role);

    List<Object[]> findUserAndDetailsById(Long userId);
}