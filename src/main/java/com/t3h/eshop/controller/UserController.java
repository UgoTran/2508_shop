package com.t3h.eshop.controller;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/my_profile")
    public ModelAndView showMyProfile() {
        Integer currentUserId = 1;

        UserInfo user = userService.getUserById(currentUserId);

        ModelAndView view = new ModelAndView("user/my_profile");

        view.addObject("user", user != null ? user : new UserInfo());

        return view;
    }
}