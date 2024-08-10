package com.spring_cloud.eureka.client.product.products;

import com.spring_cloud.eureka.client.product.core.Product;
import com.spring_cloud.eureka.client.product.dto.RequestProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @CircuitBreaker(name="createProduct", fallbackMethod = "fallbackCreateProduct")
    public void createProduct(RequestProductDto requestProductDto) {
        log.info("Creating product : {}", requestProductDto);
        productRepository.save(new Product(requestProductDto));
    }

    @CircuitBreaker(name="AllProduct", fallbackMethod = "fallbackAllProduct")
    public ResponseEntity<List<Product>> getProducts() {
        return  new ResponseEntity<>(productRepository.findAll(),HttpStatus.OK);
    }

    // order service로 부터오는 상품 존재 확인 메소드
    public boolean checkProduct(Long productId) {
        return productRepository.existsById(productId);
    }

    private ResponseEntity<String> fallbackCreateProduct(Throwable t) {
        // 여기서는 이메일 이나 다른 개발자에게 보내는 알림을 로직을 수행하고 예외처를 통한 오류메시지를 전달 하도록 변경이 필요
        return new ResponseEntity<>("상품 등록 실패", HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> fallbackAllProduct(Throwable t) {
        // 여기서는 이메일 이나 다른 개발자에게 보내는 알림을 로직을 수행하고 예외처를 통한 오류메시지를 전달 하도록 변경이 필요
        return new ResponseEntity<String>("상품 조회 실패", HttpStatus.NOT_FOUND);
    }


}
