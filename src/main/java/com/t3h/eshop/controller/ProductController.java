package com.t3h.eshop.controller;

import com.t3h.eshop.service.ProductService;
import com.t3h.eshop.storage.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // HIỂN THỊ DANH SÁCH
    @GetMapping("")
    public ModelAndView showAllProducts(@RequestParam(value = "keyword", required = false) String keyword,
                                        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        // 1. Khởi tạo ModelAndView với tên view
        ModelAndView mav = new ModelAndView("admin/product/products_list");

        int pageSize = 5; // Số lượng sản phẩm trên 1 trang (bạn có thể đổi thành 10)
        Page<Product> page;

        if (keyword != null && !keyword.isEmpty()) {
            page = productService.searchProducts(keyword, pageNo, pageSize);
        } else {
            page = productService.getAllProducts(pageNo, pageSize);
        }

        mav.addObject("products", page.getContent());

        // Các thông số cần thiết cho thanh phân trang
        mav.addObject("currentPage", pageNo);
        mav.addObject("totalPages", page.getTotalPages());
        mav.addObject("totalItems", page.getTotalElements());
        mav.addObject("keyword", keyword); // Để giữ lại từ khóa khi chuyển trang

        return mav;
    }

    // FORM THÊM MỚI
    @GetMapping("/add")
    public ModelAndView showAddFm() {
        ModelAndView mav = new ModelAndView("admin/product/add_products_form");
        mav.addObject("product", new Product());
        return mav;
    }

    // FORM SỬA
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/product/edit_products_form");
        Product product = productService.getProductById(id);
        mav.addObject("product", product);
        return mav;
    }

    // XỬ LÝ LƯU
    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        // Khởi tạo ModelAndView hướng về trang danh sách (redirect)
        ModelAndView mav = new ModelAndView("redirect:/admin/products");

        if(product.getProductId() != null){
            productService.updateProduct(product.getProductId(), product);
            redirectAttributes.addFlashAttribute("message", "Cập nhật sản phẩm thành công!");
        } else {
            productService.createProduct(product);
            redirectAttributes.addFlashAttribute("message", "Thêm mới sản phẩm thành công!");
        }

        return mav;
    }

    // XÓA
    @PostMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/admin/products");

        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("message", "Đã xóa sản phẩm thành công!");

        return mav;
    }

    // XEM CHI TIẾT
    @GetMapping("/{id}")
    public ModelAndView showProduct(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/product/products_form");

        Product product = productService.getProductById(id);
        mav.addObject("product", product);

        return mav;
    }

    // ON/OFF TRẠNG THÁI
    @GetMapping("/toggle-status/{id}")
    public ModelAndView toggleStatus(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/admin/products");

        productService.toggleProductStatus(id);
        redirectAttributes.addFlashAttribute("message", "Đã thay đổi trạng thái sản phẩm!");

        return mav;
    }
}