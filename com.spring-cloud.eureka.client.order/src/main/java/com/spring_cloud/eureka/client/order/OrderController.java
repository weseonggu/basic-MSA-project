package com.spring_cloud.eureka.client.order;

import com.spring_cloud.eureka.client.order.core.Order;
import com.spring_cloud.eureka.client.order.dto.OrderRequestDto;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) {

        return orderService.getOrder(orderId);
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderRequestDto order) {
        orderService.addOrder(order);
    }
}
