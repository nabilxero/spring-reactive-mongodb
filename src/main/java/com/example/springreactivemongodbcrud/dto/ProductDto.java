package com.example.springreactivemongodbcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
}
