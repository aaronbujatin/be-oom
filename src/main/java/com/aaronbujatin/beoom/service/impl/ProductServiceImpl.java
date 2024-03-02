package com.aaronbujatin.beoom.service.impl;

import com.aaronbujatin.beoom.dto.ProductDto;
import com.aaronbujatin.beoom.entitiy.Product;
import com.aaronbujatin.beoom.exception.ResourceNotFoundException;
import com.aaronbujatin.beoom.mapper.ProductMapper;
import com.aaronbujatin.beoom.repository.ProductRepository;
import com.aaronbujatin.beoom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;
   private final ProductMapper productMapper;
    @Override
    public ProductDto save(ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBlooms(productDto.getBlooms());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        productRepository.save(product);

        return productMapper.apply(product);
    }

    @Override
    public ProductDto getProductById(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " does not found"));
        return productMapper.apply(product);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " does not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " does not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBlooms(productDto.getBlooms());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        productRepository.save(product);
        return productMapper.apply(product);
    }

    @Override
    public List<ProductDto> getProductByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(productMapper)
                .collect(Collectors.toList());
    }
}
