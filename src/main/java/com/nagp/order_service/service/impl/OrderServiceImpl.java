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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service("order-service")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public List<OrderDto> getAllOrders(String sellerId) {
        List<Order> orderList = orderRepository.findBySellerId(sellerId);
        return orderList.stream().map(DtoConversion::convertToDto).toList();
    }

    @Override
    public  List<OrderDto> getOrderById(Integer orderId, String sellerId) {
        List<Order> orderList = orderRepository.findByCustomerId(sellerId);
        return orderList.stream().map(DtoConversion::convertToDto).toList();

    }

    @Override
    public  OrderDto getOrderById(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            return null;
        }
        return DtoConversion.convertToDto(optionalOrder.get());

    }
    @Override
    @Transactional
    public String createOrder(OrderDto orderDto) {
        Order order = DtoConversion.convertToEntity(orderDto);
        order.setStatus("CREATED");
        order.setOrderId(UUID.randomUUID().toString());
        orderRepository.save(order);
        int saveCount = 0;
        if(Objects.nonNull(orderDto) && !orderDto.getProducts().isEmpty()){
            for(OrderDto.ProductDto productDto:orderDto.getProducts()){
                InventoryDto inventoryDto = new InventoryDto();
                inventoryDto.setProductId(productDto.getProductId());
                inventoryDto.setStockCount(productDto.getQuantity());
                inventoryDto.setSellerId(productDto.getSellerId());
                InventoryDto updatedInventory = inventoryClient.updateInventory(inventoryDto);
                if(Objects.nonNull(updatedInventory)){
                    saveCount++;
                }
            }
        }
        if(saveCount>0){
            return "SUCCESS";
        }

        return null;
    }

    @Override
    public OrderDto updateOrder(Integer orderId, String action) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(action);
            orderRepository.save(order);
        }

        return null;

    }
}
