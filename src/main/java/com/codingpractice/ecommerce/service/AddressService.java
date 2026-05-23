package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.AddressDTO;
import com.codingpractice.ecommerce.model.Users;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, Users user);
}
