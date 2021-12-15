package com.example.springreactivemongodbcrud.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
}
