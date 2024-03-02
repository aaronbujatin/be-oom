package com.aaronbujatin.beoom.service;

import com.aaronbujatin.beoom.dto.ProductDto;

import java.util.List;


public interface ProductService {

    ProductDto save(ProductDto productDto);

    ProductDto getProductById(Long id);

    List<ProductDto> getAllProduct();

    void deleteProductById(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto);

    List<ProductDto> getProductByCategory(String category);
}
