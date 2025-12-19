package com.t3h.eshop.controller;

import com.t3h.eshop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/update-quantity")
    @ResponseBody
    public String updateQuantity(@RequestParam Integer cartItemId,
                                 @RequestParam Integer quantity) {
        cartItemService.updateQuantity(cartItemId, quantity);
        return "OK";
    }
}
