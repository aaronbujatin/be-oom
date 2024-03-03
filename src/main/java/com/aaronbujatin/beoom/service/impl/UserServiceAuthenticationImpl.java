package com.aaronbujatin.beoom.service.impl;

import com.aaronbujatin.beoom.dto.UserAuthenticationRequest;
import com.aaronbujatin.beoom.dto.UserAuthenticationResponse;
import com.aaronbujatin.beoom.dto.UserDto;
import com.aaronbujatin.beoom.entitiy.Role;
import com.aaronbujatin.beoom.entitiy.User;
import com.aaronbujatin.beoom.exception.ResourceNotFoundException;
import com.aaronbujatin.beoom.jwt.JwtService;
import com.aaronbujatin.beoom.mapper.UserMapper;
import com.aaronbujatin.beoom.repository.RoleRepository;
import com.aaronbujatin.beoom.repository.UserRepository;
import com.aaronbujatin.beoom.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserServiceAuthenticationImpl implements UserAuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDto register(UserDto userDto) {

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return userMapper.apply(user);
    }

    @Override
    public UserAuthenticationResponse login(UserAuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
        String token = jwtService.generateToken(authentication.getName());
        return new UserAuthenticationResponse(token);
    }

    @Override
    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " was not found"));
    }
}
