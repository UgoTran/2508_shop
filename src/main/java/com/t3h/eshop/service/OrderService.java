package com.t3h.eshop.service;

import com.t3h.eshop.storage.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrdersByUserId(Integer userId);
}