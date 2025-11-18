package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Integer> {
    List<Refund> findByOrderId(String orderId);

    List<Refund> findByStatus(String status);

    List<Refund> findByProductId(String productId);

    boolean existsByOrderId(String orderId);
}
