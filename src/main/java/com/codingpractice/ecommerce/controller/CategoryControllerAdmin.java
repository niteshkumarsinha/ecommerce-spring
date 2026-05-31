package com.codingpractice.ecommerce.controller;


import com.codingpractice.ecommerce.dto.CategoryDTO;
import com.codingpractice.ecommerce.model.Category;
import com.codingpractice.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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


    @Tag(name = "Category APIs", description = "APIs for managing categories")
    @Operation(summary = "Create category", description = "API to create a new category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category is created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Parameter(description = "Category you wish to create") @Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    @Tag(name = "Category APIs", description = "APIs for managing categories")
    public ResponseEntity<CategoryDTO> updateCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            @PathVariable Long categoryId
    ){
        CategoryDTO savedCategory = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);

    }

    @DeleteMapping("/{categoryId}")
    @Tag(name = "Category APIs", description = "APIs for managing categories")
    public ResponseEntity<CategoryDTO> deleteCategory(@Parameter(description = "Category you wish to delete") @PathVariable Long categoryId){
        CategoryDTO categoryDTO = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

}
