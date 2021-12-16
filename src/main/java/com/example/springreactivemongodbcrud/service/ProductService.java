package com.example.springreactivemongodbcrud.service;

import com.example.springreactivemongodbcrud.dto.ProductDto;
import com.example.springreactivemongodbcrud.model.Product;
import com.example.springreactivemongodbcrud.repository.ProductRepository;
import com.example.springreactivemongodbcrud.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.font.NumericShaper;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductInRange(Double min, Double max) {

//        confusing?
        return productRepository.findByPriceBetween(Range.closed(min, max));

//        This might work ----
//        return productRepository.findAllByPriceBetween(min, max).map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository
                .findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }




}
