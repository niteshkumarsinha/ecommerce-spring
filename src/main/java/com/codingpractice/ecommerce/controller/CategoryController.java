package com.codingpractice.ecommerce.controller;


import com.codingpractice.ecommerce.dto.CategoryRequest;
import com.codingpractice.ecommerce.model.Category;
import com.codingpractice.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        categoryService.createCategory(category);
        return new ResponseEntity<>("cateoory added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(
            @Valid @RequestBody CategoryRequest categoryRequest,
            @PathVariable Long categoryId
    ){

        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        Category savedCategory = categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>("Category with category id: " + savedCategory.getCategoryId(), HttpStatus.OK);

    }

}
