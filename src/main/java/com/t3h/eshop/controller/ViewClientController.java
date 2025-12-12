package com.t3h.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewClientController {
    @GetMapping("/category")
    public String viewCategoryPage() {
        return "forward:/category.html";
    }

    @GetMapping("/category/{categoryTitle}")
    public String viewSubCategoryPage(@PathVariable String categoryTitle) {
        return "forward:/sub-category.html";
    }

    @GetMapping("/category/{categoryTitle}/{subTitle}")
    public String viewProductListPage(@PathVariable String categoryTitle, @PathVariable String subTitle) {
        return "forward:/product.html";
    }

    @GetMapping("/product/{productTitle}")
    public String viewProductDetailPage(@PathVariable String productTitle) {
        return "forward:/product-detail.html";
    }

}
