package com.spring_cloud.eureka.client.order;

import com.spring_cloud.eureka.client.order.core.OrderMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMappingRepository extends JpaRepository<OrderMapping, Long> {
}
