package com.t3h.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewAdminController {

    //Admin
    @GetMapping("/admin/category")
    public String viewAdminCategory(){
        return "forward:/admin/category.html";
    }

    @GetMapping("/admin/sub-category")
    public String viewAdminSubCategory() {
        return "forward:/admin/sub-category.html";
    }

    @GetMapping("/admin/product")
    public String viewAdminProduct() {
        return "forward:/admin/product.html";
    }
}