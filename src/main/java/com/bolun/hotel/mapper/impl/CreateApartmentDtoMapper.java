package com.bolun.hotel.mapper.impl;

import com.bolun.hotel.entity.enums.ApartmentStatus;
import com.bolun.hotel.entity.enums.ApartmentType;
import com.bolun.hotel.mapper.Mapper;
import com.bolun.hotel.entity.Apartment;
import com.bolun.hotel.dto.CreateApartmentDto;

import java.math.BigDecimal;

public class CreateApartmentDtoMapper implements Mapper<CreateApartmentDto, Apartment> {

    private static final CreateApartmentDtoMapper INSTANCE = new CreateApartmentDtoMapper();

    private static final String IMAGE_PARENT_FOLDER = "apartments/";

    @Override
    public Apartment mapFrom(CreateApartmentDto object) {
        return Apartment.builder()
                .numberOfRooms(Integer.parseInt(object.numberOfRooms()))
                .numberOfSeats(Integer.parseInt(object.numberOfSeats()))
                .pricePerHour(BigDecimal.valueOf(Double.parseDouble(object.pricePerHour())))
                .photo(IMAGE_PARENT_FOLDER.concat(object.photo().getSubmittedFileName()))
                .status(ApartmentStatus.valueOf(object.status()))
                .type(ApartmentType.valueOf(object.type()))
                .build();
    }

    public static CreateApartmentDtoMapper getInstance() {
        return INSTANCE;
    }
}
