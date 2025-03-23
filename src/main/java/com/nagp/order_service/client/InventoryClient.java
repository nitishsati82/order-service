package com.nagp.order_service.client;

import com.nagp.order_service.config.ServiceUrlConfig;
import com.nagp.order_service.model.dto.InventoryDto;
import com.nagp.order_service.model.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class InventoryClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("service-integration.inventory-service-url")
    String inventoryServiceUrl;

    @Autowired
    private ServiceUrlConfig serviceUrlConfig;

    public InventoryDto updateInventory(InventoryDto inventoryDto) {
        HttpEntity<InventoryDto> requestBody = new HttpEntity<>(inventoryDto, getHeaders());
        return restTemplate.postForObject(serviceUrlConfig.getInventoryServiceUrl() + "/order-inventory-update", requestBody,
                InventoryDto.class);
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
