package com.example.payment_service.dto;


import lombok.Data;
 
@Data
public class CreatePaymentRequest {
 
    private Long orderId;
    private Long userId;
    private Double amount;
}
 