package com.task2.blogapp.servicesImpl;

import com.task2.blogapp.entities.Address;
import com.task2.blogapp.exception.ResourceNotFoundException;
import com.task2.blogapp.mappers.AddressMapper;
import com.task2.blogapp.payload.AddressDto;
import com.task2.blogapp.repositories.AddressRepo;
import com.task2.blogapp.services.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepo addressRepo, AddressMapper addressMapper) {
        this.addressRepo = addressRepo;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = addressMapper.toEntity(addressDto);

        Address savedAddress = addressRepo.save(address);

        AddressDto savedAddressDto = addressMapper.toDto(savedAddress);

        return savedAddressDto;

    }


    @Override
    public AddressDto updateAddress(AddressDto addressDto, Integer addressId) {
        Address address = this.addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "Id", addressId));
        address.setName(addressDto.getName());

        Address updatedAddress = this.addressRepo.save(address);
        AddressDto addressDto1 = addressMapper.toDto(updatedAddress);
        return addressDto1;
    }

    //
    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = this.addressRepo.findAll();
        List<AddressDto> addressDtos = addresses.stream().map(address -> addressMapper.toDto(address)).collect(Collectors.toList());
        return addressDtos;

    }

    //
    @Override
    public void deleteAddress(Integer addressId) {
        Address address = this.addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("address", "Id", addressId));
        this.addressRepo.delete(address);


    }

    //
    @Override
    public AddressDto getAddressById(Integer addressId) {
        Address address = this.addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address", "Id", addressId));

        return addressMapper.toDto(address);

    }

}
