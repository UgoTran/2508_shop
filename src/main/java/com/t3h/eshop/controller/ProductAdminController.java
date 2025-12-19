package com.t3h.eshop.controller;

import com.t3h.eshop.service.ProductService;
import com.t3h.eshop.storage.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin(origins = "*")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    //Read all
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    //Read one
    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Integer id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product); // Trả về 200 OK + Dữ liệu
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Trả về 404 Not Found
        }
    }

    //Create
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product productRequest) {
        productRequest.setProductId(null);
        Product newProduct = productService.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id,@RequestBody Product productRequest) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        productRequest.setProductId(id);

        Product updatedProduct = productService.update(id, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable Integer id) {
        try {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}