package com.spring.consumer;

import com.spring.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationConsumer {

    private final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    @KafkaListener(topics = "order-placed", groupId = "notification-group")
    public void consumeOrderEvent(OrderEvent orderEvent) {
        logger.info("Received Order Event: " + orderEvent);
        // Implement logic to send email/SMS here
        System.out.println("Notification sent for Order ID: " + orderEvent.getId());
    }
}