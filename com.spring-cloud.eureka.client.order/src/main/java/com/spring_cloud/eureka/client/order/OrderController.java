package com.spring_cloud.eureka.client.order;

import com.spring_cloud.eureka.client.order.core.Order;
import com.spring_cloud.eureka.client.order.dto.OrderRequestDto;
import com.spring_cloud.eureka.client.order.dto.OrderReseponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class OrderController {
    @Value("${server.port}")
    private  String serverPort;

    private final OrderService orderService;

    /**
     * 최초 상품 등록 도메인
     * @param order
     * @return 성공 여부
     */
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto order) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        orderService.addOrder(order);
        return new ResponseEntity<>("주문이 접수 되었습니다.", headers, HttpStatus.CREATED);
    }

    /**
     * 기존 주문에 상품 추가 주문 도메인
     * @param orderId
     * @param orderDto
     * @return 성공 여부
     */
    @PutMapping("/order/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderRequestDto orderDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server-Port",serverPort);
        orderService.updateOrder(orderId, orderDto);
        return new ResponseEntity<>("상품이 주문에 추가 되었습니다.", headers, HttpStatus.CREATED);
    }

    /**
     * 주문에 조히 도메인
     * @param orderId
     * @return 주문id에 대한 주문 정보
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderReseponseDto> getOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<OrderReseponseDto>( orderService.getOneOrderInfo(orderId), HttpStatus.OK);
    }
}
