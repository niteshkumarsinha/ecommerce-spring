package com.codingpractice.ecommerce.service;


import com.codingpractice.ecommerce.dto.CategoryDTO;
import com.codingpractice.ecommerce.dto.CategoryResponse;


public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO category, Long categoryId);
}
