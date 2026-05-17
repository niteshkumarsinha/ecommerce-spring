package com.codingpractice.ecommerce.repository;

import com.codingpractice.ecommerce.model.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String username);

    boolean existsByUserName(@NotBlank @Size(min = 3, max = 20) String username);

    boolean existsByEmail(@NotBlank @Size(max = 50) @Email String email);
}
