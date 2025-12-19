package com.t3h.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    @GetMapping
    public String userPage(){
        return "forward:/admin/users.html";
    }

    @GetMapping("/profile/{id}")
    public String profilePage(@PathVariable String id){
        return "forward:/admin/user_profile.html";
    }

    @GetMapping("/add")
    public String addPage() {
        return "forward:/admin/user_add.html";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id) {
        return "forward:/admin/user_edit.html";
    }
}

