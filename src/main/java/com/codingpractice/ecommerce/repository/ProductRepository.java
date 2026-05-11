package com.codingpractice.ecommerce.repository;

import com.codingpractice.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
