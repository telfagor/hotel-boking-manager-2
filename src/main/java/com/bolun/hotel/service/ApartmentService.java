package com.bolun.hotel.service;

import com.bolun.hotel.dto.CreateApartmentDto;
import com.bolun.hotel.dto.ReadApartmentDto;

import java.io.IOException;
import java.util.List;

public interface ApartmentService {

    CreateApartmentDto save(CreateApartmentDto createApartmentDto) throws IOException;

    List<ReadApartmentDto> findAll();

    List<String> findAllImagesPaths();
}
