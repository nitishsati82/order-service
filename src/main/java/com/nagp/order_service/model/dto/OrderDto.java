package com.nagp.order_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private Integer id;
    private Double totalPrice;
    private String paymentMethod;
    private String orderId;
    private String sellerId;
    private Integer totalQuantity;
    private String orderStatus;
    private String customerId;
    private String deliveryAddress;
    private String deliveryInstructions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ProductDto> products;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class ProductDto{
        private String productId;
        private String sellerId;
        private Integer quantity;
        private Double price;
        private String productName;
    }
}
