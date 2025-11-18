package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.OrderDetails;
import com.t3h.eshop.storage.entity.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
    List<OrderDetails> findByOrderId(String orderId);

    List<OrderDetails> findByProductId(Integer productId);

}