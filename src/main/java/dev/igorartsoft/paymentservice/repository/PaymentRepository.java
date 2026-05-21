package dev.igorartsoft.paymentservice.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import dev.igorartsoft.paymentservice.model.PaymentDocument;

public interface PaymentRepository extends MongoRepository<PaymentDocument, String> {

    Optional<PaymentDocument> findByOrderId(String orderId);

    Optional<PaymentDocument> findByEventId(String eventId);
}