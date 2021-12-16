package com.example.springreactivemongodbcrud.controller;

import com.example.springreactivemongodbcrud.dto.ProductDto;
import com.example.springreactivemongodbcrud.model.Product;
import com.example.springreactivemongodbcrud.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductDto> products() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> product(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> productInRange(@RequestParam("max") Double max, @RequestParam("min") Double min) {
        return productService.getProductInRange(min,max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id) {
        return productService.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }









}
