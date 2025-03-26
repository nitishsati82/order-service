package com.nagp.order_service.repo;

import com.nagp.order_service.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findBySellerId(String sellerId);
    List<Order> findByCustomerId(String customerId);

    Optional<Order> findById(Integer id);
}
