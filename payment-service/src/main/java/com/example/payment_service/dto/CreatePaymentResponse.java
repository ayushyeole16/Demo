package com.example.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class CreatePaymentResponse {
 
    private String razorpayOrderId;
    private Double amount;
    private String currency;
}
 