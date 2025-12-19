package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.CartService;
import com.t3h.eshop.service.OrderService;
import com.t3h.eshop.storage.entity.CartItem;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;

    @Override
    public List<CartItem> getSelectedItemsForCheckout(
            Integer userId,
            HttpServletRequest request
    ) {

        List<CartItem> allItems = cartService
                .getCartByUserId(userId)
                .getItems();

        if (allItems == null) {
            return new ArrayList<>();
        }

        // 1. Lấy cookie cart_state
        String selectedIdsStr = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("cart_state".equals(c.getName())) {
                    selectedIdsStr = c.getValue();
                    break;
                }
            }
        }

        // 2. Parse selected ids
        Set<Integer> selectedIds = new HashSet<>();
        if (!selectedIdsStr.isEmpty()) {
            try {
                String json = URLDecoder.decode(selectedIdsStr, "UTF-8");
                int start = json.indexOf("[");
                int end = json.indexOf("]");
                if (start != -1 && end != -1) {
                    String idsPart = json.substring(start + 1, end);
                    for (String id : idsPart.split(",")) {
                        if (!id.trim().isEmpty()) {
                            selectedIds.add(Integer.parseInt(id.trim()));
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        List<CartItem> selectedItems = new ArrayList<>();
        for (CartItem item : allItems) {
            if (selectedIds.contains(item.getCartItemId())) {
                selectedItems.add(item);
            }
        }

        return selectedItems;
    }

    @Override
    public double calculateSubtotal(List<CartItem> items) {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getQuantity()
                    * item.getProduct().getSellingPrice();
        }
        return subtotal;
    }
}