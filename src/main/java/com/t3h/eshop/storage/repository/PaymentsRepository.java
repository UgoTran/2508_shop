package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, String> {
    Optional<Payments> findByOrderId(String orderId);

    List<Payments> findByCustomerId(Integer customerId);

    List<Payments> findByMode(String mode);

    boolean existsByOrderId(String orderId);
}