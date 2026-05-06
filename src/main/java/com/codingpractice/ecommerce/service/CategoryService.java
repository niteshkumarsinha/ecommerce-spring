package com.codingpractice.ecommerce.service;


import com.codingpractice.ecommerce.dto.CategoryRequest;
import com.codingpractice.ecommerce.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
