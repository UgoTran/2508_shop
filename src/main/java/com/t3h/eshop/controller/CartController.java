package com.t3h.eshop.controller;

import com.t3h.eshop.service.CartService;
import com.t3h.eshop.storage.entity.Cart;
import com.t3h.eshop.storage.entity.CartItem;
import com.t3h.eshop.storage.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    @GetMapping("/{userId}")
    public ModelAndView getAllCart(@PathVariable Integer userId) {
        Cart cart = cartService.getCartByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cart", cart);
        modelAndView.addObject("items", cart.getItems() != null ? cart.getItems() : new ArrayList<>());
        modelAndView.setViewName("client/cart");
        return modelAndView;
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Integer productId,
                            @RequestParam Integer quantity) {
        Integer userId = 1;

        cartService.addToCart(userId, productId, quantity);

        return "redirect:/cart/" + userId;
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Integer cartItemId) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Integer userId = item.getCart().getUserInfo().getUserId();

        cartService.removeItem(cartItemId);
        return "redirect:/cart/" + userId;
    }

}
