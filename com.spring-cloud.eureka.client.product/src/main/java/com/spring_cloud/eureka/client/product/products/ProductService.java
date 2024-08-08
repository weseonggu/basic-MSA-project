package com.spring_cloud.eureka.client.product.products;

import com.spring_cloud.eureka.client.product.core.Product;
import com.spring_cloud.eureka.client.product.dto.RequestProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(RequestProductDto requestProductDto) {
        log.info("Creating product : {}", requestProductDto);
        productRepository.save(new Product(requestProductDto));
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
