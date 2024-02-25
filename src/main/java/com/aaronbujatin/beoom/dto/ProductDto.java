package com.aaronbujatin.beoom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private String name;
    private String description;
    private String blooms;
    private BigDecimal price;
    private List<String> imageUrl;
}
