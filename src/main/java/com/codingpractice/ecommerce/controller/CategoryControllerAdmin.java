package com.codingpractice.ecommerce.controller;


import com.codingpractice.ecommerce.dto.CategoryDTO;
import com.codingpractice.ecommerce.model.Category;
import com.codingpractice.ecommerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryControllerAdmin {

    private final CategoryService categoryService;

    public CategoryControllerAdmin(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
        CategoryDTO categoryDTO = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

}
