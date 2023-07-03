package com.task2.blogapp.controller;

import com.task2.blogapp.payload.AddressDto;
import com.task2.blogapp.payload.ApiResponse;
import com.task2.blogapp.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Address")
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(
            description = "Post endpoint for Addresses",
            summary = "This is a summary for Addresss post endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )

    @PostMapping("/")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto createAddressDto = this.addressService.createAddress(addressDto);
        return new ResponseEntity<>(createAddressDto, HttpStatus.CREATED);
    }

    @Operation(
            description = "Update endpoint for Addresses",
            summary = "This is a summary for Addresss update endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )


    @PutMapping("/{addressId}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto, @PathVariable Integer addressId) {
        AddressDto updateAddressDto = this.addressService.updateAddress(addressDto, addressId);
        return ResponseEntity.ok(updateAddressDto);
    }

    @Operation(
            description = "Delete endpoint for Addresses",
            summary = "This is a summary for Addresss delete endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )


    @DeleteMapping("/{addressId}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer addressId) {
        this.addressService.deleteAddress(addressId);
        return new ResponseEntity<>(new ApiResponse("Address has been deleted successfully", true), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint for Addresses",
            summary = "This is a summary for Addresss get endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )

    @GetMapping("/")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return ResponseEntity.ok(this.addressService.getAllAddresses());
    }

    @Operation(
            description = "Get endpoint for Addresses",
            summary = "This is a summary for Addresss get endpoint",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"

                    )

            }
    )


    @GetMapping("/{addressId}")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Integer addressId) {
        return ResponseEntity.ok(this.addressService.getAddressById(addressId));

    }

}
