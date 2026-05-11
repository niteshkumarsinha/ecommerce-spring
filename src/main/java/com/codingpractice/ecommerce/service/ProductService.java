package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.ProductDTO;
import com.codingpractice.ecommerce.model.Product;

public interface ProductService {
    ProductDTO addProduct(Product product, Long categoryId);
}
