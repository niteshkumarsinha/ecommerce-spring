package com.codingpractice.ecommerce.controller;


import com.codingpractice.ecommerce.config.AppConstants;
import com.codingpractice.ecommerce.dto.CategoryResponse;
import com.codingpractice.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Tag(name = "Category APIs", description = "APIs for managing categories")
    @GetMapping
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_ORDER, required = false) String sortOrder
    ){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder));
    }


}
