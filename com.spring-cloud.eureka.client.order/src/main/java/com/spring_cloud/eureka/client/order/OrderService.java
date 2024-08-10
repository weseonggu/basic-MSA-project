package com.spring_cloud.eureka.client.order;

import com.spring_cloud.eureka.client.order.core.Order;
import com.spring_cloud.eureka.client.order.core.OrderMapping;
import com.spring_cloud.eureka.client.order.dto.OrderRequestDto;
import com.spring_cloud.eureka.client.order.dto.OrderReseponseDto;
import com.spring_cloud.eureka.client.order.error.HttpClientRequestException;
import feign.FeignException;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMappingRepository orderMappingRepository;
    private final EntityManager entityManager;

    public Boolean isProductExist(Long product_id) {
        return productClient.isProductExistToProductService(product_id);
    }

    /**
     * 상품 추가 메소드 [product-service에 상품이 있는지 검증 후 저장]
     * @param order
     */
    @Transactional
    @CacheEvict(cacheNames = "orderCache", allEntries = true)
    public void addOrder(OrderRequestDto order) {
        try {
            // 상품이 존재하는지 확인합니다.
            Boolean check = isProductExist(order.getProduct_id());
            if (check) {
                Order o = Order.builder()
                        .name(order.getName())
                        .build();
                o = orderRepository.save(o);
                entityManager.persist(o);
                OrderMapping orm = OrderMapping.builder()
                        .order(o)
                        .product_id(order.getProduct_id())
                        .build();
                orderMappingRepository.save(orm);
            } else {
                throw new NoSuchElementException("상품이 없습니다.");
            }
        }catch (FeignException fe){
            throw new HttpClientRequestException("상품 찾기에 실패했습니다.");
        }catch (NoSuchElementException ne){
            throw ne;
        }

    }

    /**
     * 주문에 상품 추가 메소드 [product-service에 상품이 있는지 검증 후 저장]
     * @param orderId
     * @param orderRequestDto
     */
    @CacheEvict(cacheNames = "orderCache", allEntries = true)
    public void updateOrder(Long orderId, OrderRequestDto orderRequestDto) {
        try {
            boolean check = isProductExist(orderRequestDto.getProduct_id());
            log.info(String.valueOf(check));
            if(check) {
                Order o = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
                OrderMapping orm = OrderMapping.builder()
                        .order(o)
                        .product_id(orderRequestDto.getProduct_id())
                        .build();
                orderMappingRepository.save(orm);
            }
            else{
                throw new NoSuchElementException("상품이 없습니다.");
            }
        }catch (FeignException fe){
            throw new HttpClientRequestException("상품 찾기에 실패했습니다.");
        }catch (NoSuchElementException ne){
            throw ne;
        }
    }

    /**
     * 주문 정보 조회
     * @param orderId
     * @return
     */
    @Cacheable(cacheNames = "orderCache", key = "args[0]")
    public OrderReseponseDto getOneOrderInfo(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        List<Long> productIds = order.getProduct_ids().stream()
                .map(OrderMapping::getProduct_id)
                .collect(Collectors.toList());

        return new OrderReseponseDto(orderId, productIds);
    }
}
