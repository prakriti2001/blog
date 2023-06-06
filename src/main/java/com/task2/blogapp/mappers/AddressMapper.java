package com.task2.blogapp.mappers;

import com.task2.blogapp.entities.Address;
import com.task2.blogapp.payload.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto toDto(Address address);

    Address toEntity(AddressDto dto);
}


