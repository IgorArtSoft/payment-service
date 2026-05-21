package dev.igorartsoft.paymentservice.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.igorartsoft.paymentservice.event.OrderEvent;

@Document(collection = "payments")
public class PaymentDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    private String orderId;

    @Indexed(unique = true)
    private String eventId;

    private Instant orderCreatedAt;
    private Instant paymentReceivedAt;
    private String status;

    private String customerId;
    private BigDecimal amount;
    private Instant createdAt;

    private String topic;
    private int partition;
    private long offset;

    private Instant receivedAt;
    
    public PaymentDocument() {
    }

    public PaymentDocument(
            String eventId,
            String orderId,
            String customerId,
            BigDecimal amount,
            Instant createdAt,
            String topic,
            int partition,
            long offset,
            Instant receivedAt
    ) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
        this.receivedAt = receivedAt;
    }
    
    public static PaymentDocument from(OrderEvent event) {
        PaymentDocument payment = new PaymentDocument();
        payment.orderId = event.orderId();
        payment.eventId = event.eventId();
        payment.customerId = event.customerId();
        payment.amount = event.amount();
        payment.orderCreatedAt = event.createdAt();
        payment.paymentReceivedAt = Instant.now();
        payment.status = "RECEIVED";
        return payment;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public Instant getPaymentReceivedAt() {
        return paymentReceivedAt;
    }

    public String getStatus() {
        return status;
    }

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public Instant getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Instant receivedAt) {
		this.receivedAt = receivedAt;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public void setOrderCreatedAt(Instant orderCreatedAt) {
		this.orderCreatedAt = orderCreatedAt;
	}

	public void setPaymentReceivedAt(Instant paymentReceivedAt) {
		this.paymentReceivedAt = paymentReceivedAt;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}