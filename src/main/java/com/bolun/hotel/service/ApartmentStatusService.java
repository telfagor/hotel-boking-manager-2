package com.bolun.hotel.service;

import com.bolun.hotel.entity.enums.ApartmentStatus;

import java.util.List;

public interface ApartmentStatusService {

    List<ApartmentStatus> findAll();
}
