package dev.igorartsoft.paymentservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    @KafkaListener(
            topics = "orders",
            groupId = "payment-service"
    )
    public void consume(OrderEvent event) {
        log.info("Payment-service received order event: {}", event);
    }
}
