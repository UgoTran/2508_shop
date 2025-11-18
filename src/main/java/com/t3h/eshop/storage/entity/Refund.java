package com.t3h.eshop.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "refund")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_id")
    private Integer refundId;

    @Column(name = "order_id", nullable = false, length = 100)
    private String orderId;

    @Column(name = "product_id", length = 100)
    private String productId;

    @Column(name = "refund_amount", nullable = false)
    private Double refundAmount;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
