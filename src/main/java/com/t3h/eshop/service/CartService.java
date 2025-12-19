package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Cart;

import java.util.List;

public interface CartService {
    Cart getCartByUserId(Integer userId);

    void addToCart(Integer userId, Integer productId, Integer quantity);

    void removeItem(Integer cartItemId);

}
