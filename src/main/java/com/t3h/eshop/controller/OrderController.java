package com.t3h.eshop.controller;

import com.t3h.eshop.service.CartService;
import com.t3h.eshop.service.OrderService;
import com.t3h.eshop.storage.entity.CartItem;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public ModelAndView checkout(HttpServletRequest request) {
        Integer userId = 1;

        List<CartItem> selectedItems =
                orderService.getSelectedItemsForCheckout(userId, request);

        double subtotal =
                orderService.calculateSubtotal(selectedItems);

        ModelAndView mv = new ModelAndView("client/checkout");
        mv.addObject("items", selectedItems);
        mv.addObject("subtotal", subtotal);
        mv.addObject("formattedSubtotal",
                String.format("%,.2f", subtotal));

        return mv;
    }
}