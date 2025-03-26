package com.nagp.order_service.convertor;

import com.nagp.order_service.model.dto.OrderDto;
import com.nagp.order_service.model.entity.Order;
import com.nagp.order_service.model.entity.OrderItems;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DtoConversion {
    public Order convertToEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setStatus(orderDto.getOrderStatus());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setSellerId(order.getSellerId());
        List<OrderItems> orderItems = orderDto.getProducts().stream().map(productDto -> {
            OrderItems item = new OrderItems();
            item.setProductId(productDto.getProductId());
            item.setSellerId(productDto.getSellerId());
            item.setQuantity(productDto.getQuantity());
            item.setPrice(productDto.getPrice());
            item.setProductName(productDto.getProductName());
            item.setOrder(order);
            order.setSellerId(productDto.getSellerId());
            return item;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return order;
    }

    public OrderDto convertToDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderStatus(order.getStatus());
        orderDto.setCreatedAt(order.getOrderDateTime());
        orderDto.setUpdatedAt(order.getModifiedAt());
        orderDto.setOrderId(order.getOrderId());

        List<OrderDto.ProductDto> products = order.getOrderItems().stream().map(item -> {
            OrderDto.ProductDto productDto = new OrderDto.ProductDto();
            productDto.setProductId(item.getProductId());
            productDto.setSellerId(item.getSellerId());
            productDto.setQuantity(item.getQuantity());
            productDto.setPrice(item.getPrice());
            productDto.setProductName(item.getProductName());
            return productDto;
        }).collect(Collectors.toList());

        orderDto.setProducts(products);
        return orderDto;
    }
}