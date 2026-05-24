package com.codingpractice.ecommerce.service;

import com.codingpractice.ecommerce.dto.AddressDTO;
import com.codingpractice.ecommerce.exceptions.ResourceNotFoundException;
import com.codingpractice.ecommerce.model.Address;
import com.codingpractice.ecommerce.model.Users;
import com.codingpractice.ecommerce.repository.AddressRepository;
import com.codingpractice.ecommerce.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper, UsersRepository usersRepository) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, Users user) {
        Address address = modelMapper.map(addressDTO, Address.class);
        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOs = addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
        return addressDTOs;
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", id));
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getUserAddresses(Users user) {
        List<Address> addresses = addressRepository.findAddressesByUser(user);
        List<AddressDTO> addressDTOS = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
        return addressDTOS;
    }

    @Override
    public AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO) {
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));
        addressFromDb.setBuildingName(addressDTO.getBuildingName());
        addressFromDb.setCity(addressDTO.getCity());
        addressFromDb.setCountry(addressDTO.getCountry());
        addressFromDb.setPincode(addressDTO.getPincode());
        addressFromDb.setState(addressDTO.getState());
        addressFromDb.setStreet(addressDTO.getStreet());
        Address updatedAddress = addressRepository.save(addressFromDb);
        Users user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        usersRepository.save(user);
        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));
        Users user = addressFromDb.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        usersRepository.save(user);
        addressRepository.delete(addressFromDb);
        return "Address deleted successfully with addressId: " + addressId;
    }

}
