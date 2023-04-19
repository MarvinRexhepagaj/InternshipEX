package com.lhind.service.impl;


import com.lhind.model.entity.User;
import com.lhind.repository.UserDetailsRepository;
import com.lhind.repository.UserRepository;
import com.lhind.repository.impl.UserDetailsRepositoryImpl;
import com.lhind.repository.impl.UserRepositoryImpl;
import com.lhind.service.UserService;



import java.util.List;
import java.util.Optional;



public class UserServiceImpl implements UserService {

    final UserRepository userRepository = new UserRepositoryImpl();


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<Object[]> findUserAndDetailsById(Long userId) {
        return userRepository.findUserAndDetailsById(userId);
    }
}
