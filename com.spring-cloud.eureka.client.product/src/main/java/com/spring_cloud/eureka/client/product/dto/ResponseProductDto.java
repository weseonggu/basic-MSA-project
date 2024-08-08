package com.spring_cloud.eureka.client.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDto {
    private Long id;
    private String name;
    private Integer price;
}
