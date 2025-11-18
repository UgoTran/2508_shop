package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {
    List<Orders> findByStatus(String status);

    List<Orders> findByExpectedDeliveryDate(LocalDate expectedDeliveryDate);

}
