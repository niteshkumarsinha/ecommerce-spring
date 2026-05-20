package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
