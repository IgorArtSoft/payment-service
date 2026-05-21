package dev.igorartsoft.paymentservice.kafka;

import java.time.Instant;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import dev.igorartsoft.paymentservice.event.OrderEvent;
import dev.igorartsoft.paymentservice.model.PaymentDocument;
import dev.igorartsoft.paymentservice.repository.PaymentRepository;

@Component
public class OrderEventConsumer {

    private final PaymentRepository repository;

    public OrderEventConsumer(PaymentRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(
            topics = "${app.kafka.orders-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(
            OrderEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
    	PaymentDocument document = new PaymentDocument(
                event.eventId(),
                event.orderId(),
                event.customerId(),
                event.amount(),
                event.createdAt(),
                topic,
                partition,
                offset,
                Instant.now()
        );

        try {
            repository.insert(document);

            System.out.println("Saved Kafka order event to MongoDB. eventId="
                    + event.eventId()
                    + ", orderId=" + event.orderId());

        } catch (DuplicateKeyException ex) {
            System.out.println("Duplicate Kafka event ignored. eventId="
                    + event.eventId()
                    + ", orderId=" + event.orderId());
        }
    }
}