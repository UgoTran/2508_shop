package com.t3h.eshop.storage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image1", columnDefinition = "TEXT")
    private String image1;

    @Column(name = "image2", columnDefinition = "TEXT")
    private String image2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_category_id", nullable = false)
    @JsonIgnoreProperties("products")
    private SubCategory subCategory;
}