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

    @GetMapping("/fetch")
    ResponseEntity< List<OrderDto>> getByOrderId(@RequestParam(value = "orderId",required = false) Integer orderId,@RequestParam(value = "customerId",required = false) String customerId){
        return ResponseEntity.ok(orderService.getOrderById(orderId,customerId));
    }

    @GetMapping("/{orderId}")
    ResponseEntity< OrderDto> getByOrderId(@PathVariable(value = "orderId") Integer orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping("/create")
    ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PostMapping("/update")
    ResponseEntity<OrderDto> updateOrder(@RequestParam("orderId") Integer orderId,@RequestParam(value = "action",required = false) String action){
        return ResponseEntity.ok(orderService.updateOrder(orderId,action));
    }
}
