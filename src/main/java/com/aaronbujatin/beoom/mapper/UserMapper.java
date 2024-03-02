package com.aaronbujatin.beoom.mapper;

import com.aaronbujatin.beoom.dto.UserDto;
import com.aaronbujatin.beoom.entitiy.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(user.getName(), user.getUsername(), user.getPassword());
    }
}
