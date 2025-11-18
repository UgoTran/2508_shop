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
@Table(name = "customer_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "house_no", length = 100)
    private String houseNo;

    @Column(name = "road_area", length = 200)
    private String roadArea;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "user_id")
    private Integer userId;
}
