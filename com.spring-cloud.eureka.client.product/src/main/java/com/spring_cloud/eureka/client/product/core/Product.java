package com.spring_cloud.eureka.client.product.core;

import com.spring_cloud.eureka.client.product.dto.RequestProductDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;

    public Product(RequestProductDto requestProductDto) {
        this.name = requestProductDto.getName();
        this.price = requestProductDto.getPrice();
    }
}
