package com.nagp.order_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    private String sellerId;
    private String productId;
    private String productName;
    private Integer quantity;
    private String customerId;
    private Double totalPrice;
    private String deliveryAddress;
    private String deliveryInstructions;

    private String paymentMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;


    private String status;  // E.g., "Pending", "Shipped", "Delivered"

    @CreatedDate
    protected LocalDateTime orderDateTime;

    @LastModifiedDate
    protected LocalDateTime modifiedAt;

    private LocalDateTime getCurrentTimeStamp() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
    }

    @PrePersist
    public void prePersist() {
        this.orderDateTime = this.modifiedAt = getCurrentTimeStamp();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = getCurrentTimeStamp();
    }
}

