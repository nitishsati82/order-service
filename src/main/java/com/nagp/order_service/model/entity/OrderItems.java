package com.nagp.order_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productId;
    private String sellerId;
    private Integer quantity;
    private Double price;
    private String productName;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
