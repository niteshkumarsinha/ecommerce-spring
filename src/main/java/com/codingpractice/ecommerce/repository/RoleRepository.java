package com.codingpractice.ecommerce.repository;

import com.codingpractice.ecommerce.model.AppRole;
import com.codingpractice.ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}
