package com.aaronbujatin.beoom.dto;

import com.aaronbujatin.beoom.entitiy.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {

    private int quantity;
    private Product product;

}
