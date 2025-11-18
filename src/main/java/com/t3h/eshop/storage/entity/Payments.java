package com.t3h.eshop.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payments {
    @Id
    @Column(name = "payment_id", length = 50)
    private String paymentId;

    @Column(name = "order_id", nullable = false, length = 20)
    private String orderId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "paymentDate", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "mode", nullable = false, length = 20)
    private String mode;
}