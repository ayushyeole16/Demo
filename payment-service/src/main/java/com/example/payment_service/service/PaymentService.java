package com.example.payment_service.service;


import com.example.payment_service.client.OrderClient;
import com.example.payment_service.dto.*;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.repository.PaymentRepository;
import com.razorpay.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 
import java.time.LocalDateTime;
 
@Service
@RequiredArgsConstructor
public class PaymentService {
 
    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
 
    @Value("${razorpay.secret}")
    private String secret;
 
    public CreatePaymentResponse createPayment(CreatePaymentRequest request)
            throws RazorpayException {
 
        JSONObject options = new JSONObject();
        options.put("amount", request.getAmount() * 100);
        options.put("currency", "INR");
 
        Order razorpayOrder = razorpayClient.orders.create(options);
 
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .razorpayOrderId(razorpayOrder.get("id"))
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
 
        paymentRepository.save(payment);
 
        return new CreatePaymentResponse(
                razorpayOrder.get("id"),
                request.getAmount(),
                "INR"
        );
    }
 
    public String verifyPayment(VerifyPaymentRequest request) {
 
        String payload =
                request.getRazorpayOrderId() + "|" +
                request.getRazorpayPaymentId();
 
        String generatedSignature =
                HmacUtils.hmacSha256Hex(secret, payload);
 
        if (!generatedSignature.equals(request.getSignature())) {
            throw new RuntimeException("Payment verification failed");
        }
 
        Payment payment = paymentRepository
                .findByRazorpayOrderId(request.getRazorpayOrderId())
                .orElseThrow();
 
        payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
        payment.setStatus("SUCCESS");
        payment.setUpdatedAt(LocalDateTime.now());
 
        paymentRepository.save(payment);
 
        orderClient.updateOrderStatus(payment.getOrderId(), "CONFIRMED");
 
        return "Payment verified successfully";
    }
}

 
