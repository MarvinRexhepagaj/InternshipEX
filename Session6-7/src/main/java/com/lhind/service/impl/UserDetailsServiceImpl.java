package com.lhind.service.impl;

import com.lhind.model.entity.UserDetails;
import com.lhind.repository.UserDetailsRepository;
import com.lhind.repository.impl.UserDetailsRepositoryImpl;
import com.lhind.service.UserDetailsService;

import java.util.List;
import java.util.Optional;


public class UserDetailsServiceImpl implements UserDetailsService {

    final UserDetailsRepository userDetailsRepository = new UserDetailsRepositoryImpl();



    @Override
    public Optional<UserDetails> findById(Long id) {
        return userDetailsRepository.findById(id);
    }

    @Override
    public List<UserDetails> findAll() {
        return userDetailsRepository.findAll();
    }

    @Override
    public void save(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public void delete(UserDetails userDetails) {
        userDetailsRepository.delete(userDetails);
    }

    @Override
    public List<UserDetails> findByPhoneNumber(String phoneNumber) {
        return userDetailsRepository.findByPhoneNumber(phoneNumber);
    }
}