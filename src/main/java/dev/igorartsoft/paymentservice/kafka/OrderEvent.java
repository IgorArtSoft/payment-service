package dev.igorartsoft.paymentservice.kafka;

import java.math.BigDecimal;

public class OrderEvent {

    private String orderId;
    private String productName;
    private BigDecimal amount;

    public OrderEvent() {
    }

    public OrderEvent(String orderId, String productName, BigDecimal amount) {
        this.orderId = orderId;
        this.productName = productName;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}