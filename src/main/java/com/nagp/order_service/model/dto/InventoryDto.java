package com.nagp.order_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {
    private String productId;
    private String sellerId;
    private Integer stockCount;
    private String location;
}
