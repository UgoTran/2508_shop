package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.OrderService;
import com.t3h.eshop.storage.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {
        // --- MOCK DATA (Giả lập dữ liệu) ---
        List<OrderDTO> orders = new ArrayList<>();

        // 1. Tạo Đơn hàng 1 (Đang giao)
        List<OrderDTO.OrderItemDTO> items1 = new ArrayList<>();
        items1.add(OrderDTO.OrderItemDTO.builder()
                .id(101).name("Boys Lace Sneakers (Dark Blue)")
                .price(17.94).qty(1).size("6").image("product1.jpg")
                .isCancelled(false).build());

        items1.add(OrderDTO.OrderItemDTO.builder()
                .id(102).name("Alloy Gold-plated Kada")
                .price(4.80).qty(1).image("product2.jpg")
                .isCancelled(true)
                .cancelDate("27-03-2025 09:47 PM").cancelReason("Changed my mind").build());

        orders.add(OrderDTO.builder()
                .id("ORD3vHvf23H717").date("Thu, Apr 24th '25")
                .total(22.74).status("Placed").expectedDate("May 01")
                .items(items1).build());

        // 2. Tạo Đơn hàng 2 (Đã hủy)
        List<OrderDTO.OrderItemDTO> items2 = new ArrayList<>();
        items2.add(OrderDTO.OrderItemDTO.builder()
                .id(103).name("Divas combo kit")
                .price(7.00).qty(1).image("product3.jpg")
                .isCancelled(true)
                .cancelDate("26-11-2025 04:51 PM").cancelReason("Duplicate order").build());

        orders.add(OrderDTO.builder()
                .id("ORDICgBQFIQ34").date("Mon, Aug 4th '25")
                .total(7.00).status("Cancelled").expectedDate("Cancelled")
                .items(items2).build());

        return orders;
    }
}