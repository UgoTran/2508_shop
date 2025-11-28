package com.t3h.eshop.storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    //ID sản phẩm
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    //Tên sản phẩm, cột này không thể null được
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "product_type")
    private String productType;

    @Column(nullable = false)
    private BigDecimal productPrice;

    @Column(name = "is_active")
    private boolean isActive;
}
