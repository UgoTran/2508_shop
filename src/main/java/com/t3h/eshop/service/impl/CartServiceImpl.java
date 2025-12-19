package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.CartService;
import com.t3h.eshop.storage.entity.Cart;
import com.t3h.eshop.storage.entity.CartItem;
import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.entity.UserInfo;
import com.t3h.eshop.storage.repository.CartItemRepository;
import com.t3h.eshop.storage.repository.CartRepository;
import com.t3h.eshop.storage.repository.ProductRepository;
import com.t3h.eshop.storage.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Cart getCartByUserId(Integer userId) {
        Cart cart = cartRepository.findCartByUserInfoUserId(userId);
        if(cart == null) {
            UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found: " + userId));
            cart = new Cart();
            cart.setUserInfo(userInfo);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public void addToCart(Integer userId, Integer productId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not found: " + productId));
        Optional<CartItem> optionalCartItem = cart.getItems().stream().filter(i -> i.getProduct()
                        .getProductId()
                        .equals(productId))
                        .findFirst();

        if(optionalCartItem.isEmpty()) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getItems().add(cartItem);
        } else {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeItem(Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Cart cart = cartItem.getCart();
        cart.getItems().remove(cartItem);

        cartItemRepository.delete(cartItem);
    }

}
