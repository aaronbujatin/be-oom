package com.aaronbujatin.beoom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAuthenticationRequest {

    private String username;
    private String password;


}
