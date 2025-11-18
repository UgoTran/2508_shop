package com.t3h.eshop.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetailsId.class)
@Builder
public class OrderDetails {
    @Id
    @Column(name = "order_id", length = 20)
    private String orderId;

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "price_each", nullable = false)
    private Double priceEach;
}
