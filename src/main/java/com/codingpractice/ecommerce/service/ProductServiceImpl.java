package com.codingpractice.ecommerce.service;


import com.codingpractice.ecommerce.dto.ProductDTO;
import com.codingpractice.ecommerce.exceptions.ResourceNotFoundException;
import com.codingpractice.ecommerce.model.Category;
import com.codingpractice.ecommerce.model.Product;
import com.codingpractice.ecommerce.repository.CategoryRepository;
import com.codingpractice.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO addProduct(Product product, Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        product.setProductId(null);
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        ProductDTO productDTO = modelMapper.map(savedProduct, ProductDTO.class);
        return productDTO;
    }
}
