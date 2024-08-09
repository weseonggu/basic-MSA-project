package com.spring_cloud.eureka.client.product.products;

import com.spring_cloud.eureka.client.product.core.Product;
import com.spring_cloud.eureka.client.product.dto.RequestProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    @Value("${server.port}")
    private  String serverPort;

    private  final ProductService productService;
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(
            @RequestBody RequestProductDto requestProductDto,
            @RequestHeader(value = "X-User-Id",required = true) String userId,
            @RequestHeader(value = "X-Role", required = true) String role
            ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        log.info("addProduct: {} {} {}" ,userId,role, requestProductDto);
        productService.createProduct(requestProductDto);
        return  new ResponseEntity("Product added successfully", headers, HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        return  productService.getProducts();
    }
}
