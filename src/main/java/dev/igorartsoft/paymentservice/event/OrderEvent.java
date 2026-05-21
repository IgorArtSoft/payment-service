package dev.igorartsoft.paymentservice.event;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderEvent(
        String eventId,
        String orderId,
        String customerId,
        BigDecimal amount,
        Instant createdAt
) {
}