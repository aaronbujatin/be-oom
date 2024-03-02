package com.aaronbujatin.beoom.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String blooms;
    private String category;
    private BigDecimal price;
    @Column(length = 500000)
    private List<String> imageUrl;

    public Product(String name, String description, String blooms, BigDecimal price, List<String> imageUrl) {
        this.name = name;
        this.description = description;
        this.blooms = blooms;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}

