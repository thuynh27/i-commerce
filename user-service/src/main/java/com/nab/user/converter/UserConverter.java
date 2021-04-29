package com.nab.user.converter;

import com.nab.user.dto.UserDTO;
import com.nab.user.model.UserAuthority;

/**
 * Converts UserDTO to User Entity.
 * Converts User entity to UserDTO
 */
public class UserConverter extends BaseConverter<UserDTO, UserAuthority> {

    private static UserConverter instance = new UserConverter();

    public static UserConverter getInstance() {
        return UserConverter.instance;
    }

    private UserConverter() {
        super(userDTO -> new UserAuthority(userDTO.getUserName() , userDTO.getEmail() ,userDTO.getPassword()),
            user -> new UserDTO(user.getName() , user.getEmail()));
    }
}
