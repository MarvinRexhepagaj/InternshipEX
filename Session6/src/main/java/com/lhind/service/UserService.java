package com.lhind.service;

import com.lhind.model.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    List<User> findAll();

    void save(User user);

    void delete(User user);

    Optional<User> findByUsername(String username);

    List<User> findByRole(String role);

    List<Object[]> findUserAndDetailsById(Long userId);
}
