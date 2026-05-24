package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.AddressDTO;
import com.codingpractice.ecommerce.model.Users;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, Users user);

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long id);

    List<AddressDTO> getUserAddresses(Users user);

    AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO);
}
