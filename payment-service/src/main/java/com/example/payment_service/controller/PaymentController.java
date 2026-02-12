package com.example.payment_service.controller;


import com.example.payment_service.dto.*;
import com.example.payment_service.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
 
    private final PaymentService paymentService;
 
    @PostMapping("/create")
    public CreatePaymentResponse createPayment(
            @RequestBody CreatePaymentRequest request
    ) throws RazorpayException {
 
        return paymentService.createPayment(request);
    }
 
    @PostMapping("/verify")
    public String verifyPayment(
            @RequestBody VerifyPaymentRequest request
    ) {
        return paymentService.verifyPayment(request);
    }
}
 
