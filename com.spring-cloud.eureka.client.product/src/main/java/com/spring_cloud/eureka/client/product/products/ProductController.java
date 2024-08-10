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

    /**
     * 상품 추가 도메인 
     * @param requestProductDto 등록에 필요한 정보
     * @param userId 
     * @param role
     * @return 성공 200 코드
     */
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

    /**
     * 상품 정보 전체 조회  도메인
     * @return 전체 상품 정보
     */
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        return  new ResponseEntity(productService.getProducts(), headers, HttpStatus.OK);
    }

    /**
     * 다른 서비스의 요청[상품이 있는지 검증]
     * @param product_id 상품 id
     * @return boolean 타입
     */
    @GetMapping("/products/check/{product_id}")
    public Boolean checkProduct(@PathVariable Long product_id) {
        return productService.checkProduct(product_id);
    }
}
