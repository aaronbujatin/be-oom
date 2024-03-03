package com.aaronbujatin.beoom.exception;

public class ProductOutOfStockException extends RuntimeException{

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
