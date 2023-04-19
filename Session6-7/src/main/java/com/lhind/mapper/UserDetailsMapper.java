package com.lhind.mapper;

import com.lhind.model.dto.UserDetailsDTO;
import com.lhind.model.entity.UserDetails;

public class UserDetailsMapper extends AbstractMapper<UserDetails, UserDetailsDTO> {

    @Override
    public UserDetails toEntity(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(userDetailsDTO.getId());
        userDetails.setFirstName(userDetailsDTO.getFirstName());
        userDetails.setLastName(userDetailsDTO.getLastName());
        userDetails.setEmail(userDetailsDTO.getEmail());
        userDetails.setPhoneNumber(userDetailsDTO.getPhoneNumber());

        return userDetails;
    }

    @Override
    public UserDetailsDTO toDto(UserDetails userDetails) {
        if (userDetails == null) {
            return null;
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setId(userDetails.getId());
        userDetailsDTO.setFirstName(userDetails.getFirstName());
        userDetailsDTO.setLastName(userDetails.getLastName());
        userDetailsDTO.setEmail(userDetails.getEmail());
        userDetailsDTO.setPhoneNumber(userDetails.getPhoneNumber());

        return userDetailsDTO;
    }
}