package com.nagp.order_service.controller;

import com.nagp.order_service.model.dto.OrderDto;
import com.nagp.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    ResponseEntity<List<OrderDto>> findAllOrders(@RequestParam("sellerId") String sellerId){
        return ResponseEntity.ok(orderService.getAllOrders(sellerId));
    }

    @GetMapping("{orderId}")
    ResponseEntity<OrderDto> getByOrderId(@RequestParam("orderId") Integer orderId,@RequestParam("sellerId") String sellerId){
        return ResponseEntity.ok(orderService.getOrderById(orderId,sellerId));
    }

    @PostMapping("/create")
    ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PostMapping("/update")
    ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDetails){
        return ResponseEntity.ok(orderService.updateOrder(orderDetails.getId(),orderDetails));
    }
}
