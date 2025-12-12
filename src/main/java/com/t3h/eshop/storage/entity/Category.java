package com.t3h.eshop.storage.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image_link", columnDefinition = "TEXT")
    private String imageLink;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubCategory> subCategories;
}