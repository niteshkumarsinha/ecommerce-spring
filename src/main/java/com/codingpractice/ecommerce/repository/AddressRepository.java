package com.codingpractice.ecommerce.repository;

import com.codingpractice.ecommerce.model.Address;
import com.codingpractice.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressesByUser(Users user);
}
