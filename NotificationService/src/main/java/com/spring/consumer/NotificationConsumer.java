package com.spring.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.service.NotificationService;
import com.spring.service.EmailService;
import com.spring.client.UserClient;

@Service
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final EmailService emailService;
    private final UserClient userClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public NotificationConsumer(NotificationService notificationService,
                                EmailService emailService,
                                UserClient userClient) {
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.userClient = userClient;
    }

    @KafkaListener(topics = "orders.placed", groupId = "order-group")
    public void consumeOrderEvent(String event) {
        System.out.println("Consumer Listening for Order Events");
        System.out.println("Consumed order event: " + event);

        try {
            JsonNode node = mapper.readTree(event);
            Long orderId = node.get("orderId").asLong();
            Long customerId = node.get("customerId").asLong();
            String orderDetails = node.get("orderDetails").asText();

            // Notify the customer
            notificationService.addNotification(
                String.valueOf(customerId),
                "ORDER",
                "Your order #" + orderId + " has been placed successfully."
            );

            // Send email
            String customerEmail = userClient.getUserEmail(customerId);
            if (customerEmail != null) {
                emailService.sendNotificationEmail(
                    customerEmail,
                    "Order Placed Successfully",
                    "Dear Customer, your order #" + orderId + " has been successfully placed. Details: " + orderDetails
                );
            } else {
                System.out.println("No email found for customerId: " + customerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}