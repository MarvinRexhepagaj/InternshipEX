package com.lhind.repository;

import com.lhind.model.entity.User;
import com.lhind.model.entity.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends Repository<UserDetails, Long> {


    List<UserDetails> findByPhoneNumber(String phoneNumber);
}
