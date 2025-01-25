package com.example.shop_server.controllers;

import com.example.shop_server.model.Category;
import com.example.shop_server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    // E_CAT_10: Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    // E_CAT_20 & E_CAT_30: Update a category
    @PutMapping
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(service.updateCategory(category));
    }

    // E_CAT_40: Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    // E_CAT_50 & E_CAT_60: Get categories with filters
    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(required = false) Boolean isRoot,
            @RequestParam(required = false) Date afterDate,
            @RequestParam(required = false) Date beforeDate,
            Pageable pageable) {
        return ResponseEntity.ok(service.getCategoriesByFilter(isRoot, afterDate, beforeDate, pageable));
    }

    // E_CAT_70: Get categories sorted by child count
    @GetMapping("/sorted-by-children")
    public ResponseEntity<Page<Category>> getCategoriesSortedByChildCount(Pageable pageable) {
        return ResponseEntity.ok(service.getCategoriesSortedByChildCount(pageable));
    }

    // E_CAT_80: Get category details
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }
}
