package com.aaronbujatin.beoom.service;

import com.aaronbujatin.beoom.dto.UserAuthenticationRequest;
import com.aaronbujatin.beoom.dto.UserAuthenticationResponse;
import com.aaronbujatin.beoom.dto.UserDto;
import com.aaronbujatin.beoom.entitiy.User;

public interface UserAuthenticationService {

    UserDto register(UserDto userDto);

    UserAuthenticationResponse login(UserAuthenticationRequest authenticationRequest);

    User getAuthenticatedUser();


}
