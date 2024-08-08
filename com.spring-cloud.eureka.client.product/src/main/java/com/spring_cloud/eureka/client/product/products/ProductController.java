package com.spring_cloud.eureka.client.product.products;

import com.spring_cloud.eureka.client.product.core.Product;
import com.spring_cloud.eureka.client.product.dto.RequestProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @Value("${server.port}")
    private  String serverPort;

    private  final ProductService productService;
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody RequestProductDto requestProductDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        productService.createProduct(requestProductDto);
        return  new ResponseEntity("Product added successfully", headers, HttpStatus.OK);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        return  new ResponseEntity(productService.getProducts(), headers, HttpStatus.OK);
    }
}
