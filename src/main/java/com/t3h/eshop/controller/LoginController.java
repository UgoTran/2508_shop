package com.t3h.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/admin")
    public String loginUser() {
        return "admin_login";
    }

    @GetMapping("/client")
    public String loginClient() {
        return "client_login";
    }
}
