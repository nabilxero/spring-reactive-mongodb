package com.example.springreactivemongodbcrud.repository;

import com.example.springreactivemongodbcrud.dto.ProductDto;
import com.example.springreactivemongodbcrud.model.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

    Flux<Product> findAllByPriceBetween(Double min, Double max);

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
