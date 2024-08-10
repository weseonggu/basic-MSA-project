package com.spring_cloud.eureka.client.order;

import com.spring_cloud.eureka.client.order.core.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
