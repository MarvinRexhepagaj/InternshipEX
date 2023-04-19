package com.lhind.main;

import com.lhind.model.entity.User;
import com.lhind.model.entity.UserDetails;
import com.lhind.repository.UserRepository;
import com.lhind.repository.impl.UserRepositoryImpl;
import com.lhind.service.UserService;
import com.lhind.service.impl.UserServiceImpl;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl();

      /*  User u2 = new User();
        u2.setUsername("User2");
        u2.setPassword("User2thesecond");
        u2.setRole("Guard");


        UserDetails ud1 = new UserDetails();
        ud1.setFirstName("Marvin");
        ud1.setLastName("Rexhepagaj");
        ud1.setPhoneNumber("000040404");
        ud1.setEmail("marvin@gmail.com");
        ud1.setUser(u2);

        u2.setUserDetails(ud1);

        userService.save(u2);*/

        /*List<User> user = userService.findByRole("Guard");
        user.forEach(u -> System.out.println(u));*/


        List<User> user = userService.findByRole("Guard");
        user.forEach(u -> System.out.println(u));



    }


}
