package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.CartItemService;
import com.t3h.eshop.storage.entity.CartItem;
import com.t3h.eshop.storage.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void updateQuantity(Integer cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found: " + cartItemId));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
}
