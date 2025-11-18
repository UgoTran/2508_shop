package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Integer> {
    Optional<CustomerProfile> findByUserId(Integer userId);

    List<CustomerProfile> findByCity(String city);

    List<CustomerProfile> findByDistrict(String district);

    Optional<CustomerProfile> findByEmail(String email);
}