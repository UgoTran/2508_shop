package com.t3h.eshop.controller;

import com.t3h.eshop.service.OrderService;
import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.OrderDTO;
import com.t3h.eshop.storage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService; // Inject thêm OrderService

    @GetMapping("/my_profile")
    public ModelAndView showMyProfile() {
        Integer currentUserId = 1;
        UserInfo user = userService.getUserById(currentUserId);

        ModelAndView view = new ModelAndView("user/my_profile");
        view.addObject("user", user != null ? user : new UserInfo());
        return view;
    }

    @GetMapping("/my_orders")
    public ModelAndView showMyOrders() {
        // 1. Xác định user (Sau này lấy từ Session/Security)
        Integer currentUserId = 1;

        // 2. Gọi Service để lấy dữ liệu (Logic nằm hết ở Service)
        List<OrderDTO> orders = orderService.getOrdersByUserId(currentUserId);

        // 3. Trả về View kèm dữ liệu
        ModelAndView view = new ModelAndView("user/my_orders");
        view.addObject("orders", orders);

        return view;
    }
}