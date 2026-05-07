package com.codingpractice.ecommerce.service;


import com.codingpractice.ecommerce.dto.CategoryDTO;
import com.codingpractice.ecommerce.dto.CategoryResponse;


public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO category, Long categoryId);
}
