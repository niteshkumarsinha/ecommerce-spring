package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.ProductDTO;
import com.codingpractice.ecommerce.dto.ProductResponse;
import com.codingpractice.ecommerce.model.Product;

public interface ProductService {
    ProductDTO addProduct(ProductDTO product, Long categoryId);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO product);

    ProductDTO deleteProduct(Long productId);
}
