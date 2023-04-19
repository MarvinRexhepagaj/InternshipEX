package com.lhind.mapper;

import com.lhind.model.dto.BookingDTO;
import com.lhind.model.dto.UserDTO;
import com.lhind.model.entity.Booking;
import com.lhind.model.entity.User;
import com.lhind.model.entity.UserDetails;
import com.lhind.mapper.BookingMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMapper extends AbstractMapper<User, UserDTO> {

    private final BookingMapper bookingMapper;
    private final UserDetailsMapper userDetailsMapper;

    public UserMapper(BookingMapper bookingMapper, UserDetailsMapper userDetailsMapper) {
        this.bookingMapper = bookingMapper;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setUserDetails(userDetailsMapper.toEntity(userDTO.getUserDetailsDTO()));
        /*user.setBookings(bookingMapper.toEntity(userDTO.getBookings()));*/

        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setUserDetailsDTO(userDetailsMapper.toDto(user.getUserDetails()));
        /*userDTO.setBookings(bookingMapper.toDto(user.getBookings()));*/

        return userDTO;
    }

}