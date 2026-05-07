package com.codingpractice.ecommerce.service;

import ch.qos.logback.core.model.Model;
import com.codingpractice.ecommerce.dto.CategoryDTO;
import com.codingpractice.ecommerce.dto.CategoryResponse;
import com.codingpractice.ecommerce.exceptions.APIException;
import com.codingpractice.ecommerce.exceptions.ResourceNotFoundException;
import com.codingpractice.ecommerce.model.Category;
import com.codingpractice.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category created till now");
        }
        List<CategoryDTO> categoryDTOS = categories
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

        CategoryResponse response = new CategoryResponse();
        response.setContent(categoryDTOS);
        return response;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategoryFromDb != null){
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(category);
        CategoryDTO deleted = modelMapper.map(category, CategoryDTO.class);
        return deleted;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        existingCategory.setCategoryName(category.getCategoryName());
        Category savedCategory = categoryRepository.save(existingCategory);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

}

