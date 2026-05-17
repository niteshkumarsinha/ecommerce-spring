package com.codingpractice.ecommerce.repository;

import com.codingpractice.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String username);
}
