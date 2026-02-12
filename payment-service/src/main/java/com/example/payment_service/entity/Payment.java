package com.example.payment_service.entity;


import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDateTime;
 
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
 
    private Long orderId;
    private Long userId;
    private Double amount;
    private String paymentMethod;
 
    private String transactionId;
    private String razorpayOrderId;
    private String razorpayPaymentId;
 
    private String status;
 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
 