package com.t3h.eshop.controller;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/my_profile")
    public String showMyProfile(Model model) {
        Integer currentUserId = 1;

        try {
            UserInfo user = userService.getUserById(currentUserId);
            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("user", new UserInfo());
        }

        return "user/my_profile";
    }
}