package com.example.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
 
@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {
 
    @PutMapping("/api/orders/{orderId}/status")
    void updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status
    );
}
 