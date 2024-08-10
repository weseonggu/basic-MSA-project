package com.spring_cloud.eureka.client.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReseponseDto implements Serializable {
    private long order_id;
    private List<Long> product_ids;
}
