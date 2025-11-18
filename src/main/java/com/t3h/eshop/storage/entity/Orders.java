package com.t3h.eshop.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @Column(name = "order_id", length = 20)
    private String orderId;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "expected_delivery_date", nullable = false)
    private LocalDate expectedDeliveryDate;

    @Column(name = "canceled_date", nullable = false)
    private LocalDateTime canceledDate;

    @Column(name = "shipped_date", nullable = false)
    private LocalDateTime shippedDate;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Column(name = "sort_description", columnDefinition = "TEXT")
    private String sortDescription;
}