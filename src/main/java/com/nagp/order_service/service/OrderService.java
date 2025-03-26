package com.nagp.order_service.service;

import com.nagp.order_service.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders(String sellerId);
    List<OrderDto> getOrderById(Integer orderId,String sellerId);

    OrderDto getOrderById(Integer orderId);

    String createOrder(OrderDto order);

    OrderDto updateOrder(Integer orderId, String action);
}
