package com.aaronbujatin.beoom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;

}
