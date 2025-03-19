package com.nagp.order_service.service.impl;

import com.nagp.order_service.client.InventoryClient;
import com.nagp.order_service.convertor.DtoConversion;
import com.nagp.order_service.model.dto.InventoryDto;
import com.nagp.order_service.model.dto.OrderDto;
import com.nagp.order_service.model.entity.Order;
import com.nagp.order_service.repo.OrderRepository;
import com.nagp.order_service.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("order-service")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public List<OrderDto> getAllOrders(String sellerId) {
        //List<Order> orderList = orderRepository.findAll();

        List<Order> orders = new ArrayList<>();



        return orders.stream().map(DtoConversion::convertToDto).toList();
    }

    @Override
    public OrderDto getOrderById(Integer orderId, String sellerId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(DtoConversion::convertToDto).orElse(null);

    }

    @Override
    @Transactional
    public String createOrder(OrderDto orderDto) {
        Order order = DtoConversion.convertToEntity(orderDto);
        order.setStatus("CREATED");
        Order saved = orderRepository.save(order);
        if(Objects.nonNull(saved)){
            InventoryDto inventoryDto = new InventoryDto();
            inventoryDto.setProductId(saved.getProductId());
            inventoryDto.setStockCount(saved.getQuantity());
            inventoryDto.setSellerId(saved.getSellerId());
            InventoryDto updateInventory = inventoryClient.updateInventory(inventoryDto);
            if(Objects.nonNull(updateInventory)){
                return "SUCCESS";
            }

        }
        return null;
    }

    @Override
    public OrderDto updateOrder(Integer orderId, OrderDto orderDetails) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(orderDetails.getOrderStatus());
            orderRepository.save(order);
        }

        return null;

    }
}
