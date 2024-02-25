package com.aaronbujatin.beoom.mapper;

import com.aaronbujatin.beoom.dto.ProductDto;
import com.aaronbujatin.beoom.entitiy.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ProductMapper implements Function<Product, ProductDto> {

    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getBlooms(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}
