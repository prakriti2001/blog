package com.task2.blogapp.services;


import com.task2.blogapp.payload.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AddressService {
    AddressDto createAddress(AddressDto address);

    AddressDto updateAddress(AddressDto address, Integer addressId);

    List<AddressDto> getAllAddresses();

    AddressDto getAddressById(Integer addressId);

    void deleteAddress(Integer addressId);
}
