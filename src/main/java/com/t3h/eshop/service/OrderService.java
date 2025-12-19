package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.CartItem;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrderService {

    List<CartItem> getSelectedItemsForCheckout(
            Integer userId,
            HttpServletRequest request
    );

    double calculateSubtotal(List<CartItem> items);
}