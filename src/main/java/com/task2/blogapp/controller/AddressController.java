package com.task2.blogapp.controller;

import com.task2.blogapp.payload.AddressDto;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto createAddressDto = this.addressService.createAddress(addressDto);
        return new ResponseEntity<>(createAddressDto, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto, @PathVariable Integer addressId) {
        AddressDto updateAddressDto = this.addressService.updateAddress(addressDto, addressId);
        return ResponseEntity.ok(updateAddressDto);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer addressId) {
        this.addressService.deleteAddress(addressId);
        return new ResponseEntity<>(new ApiResponse("Address has been deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return ResponseEntity.ok(this.addressService.getAllAddresses());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Integer addressId) {
        return ResponseEntity.ok(this.addressService.getAddressById(addressId));

    }

}
