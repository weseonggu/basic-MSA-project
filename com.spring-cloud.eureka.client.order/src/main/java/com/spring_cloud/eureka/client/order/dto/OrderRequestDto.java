package com.spring_cloud.eureka.client.order.dto;

import com.spring_cloud.eureka.client.order.core.OrderMapping;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequestDto {
    private String name;
    private Long product_id;
}
