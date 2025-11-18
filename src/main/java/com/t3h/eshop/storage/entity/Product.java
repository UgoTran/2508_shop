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

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(name = "category_brand_id")
    private Integer categoryBrandId;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "product_features", columnDefinition = "TEXT")
    private String productFeatures;

    @Column(name = "selling_price")
    private Float sellingPrice;

    @Column(name = "image1", columnDefinition = "TEXT")
    private String image1;

    @Column(name = "image2", columnDefinition = "TEXT")
    private String image2;
}
