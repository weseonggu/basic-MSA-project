package com.spring_cloud.eureka.client.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// product service의 spring:application:name: 설정과 일치해야함
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/{id}")
    String getProduct(@PathVariable("id") String id);

    @GetMapping("/products/check/{product_id}")
    Boolean isProductExistToProductService(@PathVariable("product_id")Long product_id);
}


