package com.aaronbujatin.beoom.controller;

import com.aaronbujatin.beoom.dto.UserAuthenticationRequest;
import com.aaronbujatin.beoom.dto.UserAuthenticationResponse;
import com.aaronbujatin.beoom.dto.UserDto;
import com.aaronbujatin.beoom.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        UserDto response = userAuthenticationService.register(userDto);
        return new ResponseEntity<>("User Successfully registered", HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserAuthenticationResponse> login(@RequestBody UserAuthenticationRequest request){
        UserAuthenticationResponse response = userAuthenticationService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

