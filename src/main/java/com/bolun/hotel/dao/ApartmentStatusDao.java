package com.bolun.hotel.dao;

import com.bolun.hotel.entity.enums.ApartmentStatus;

import java.util.List;

public interface ApartmentStatusDao {

    List<ApartmentStatus> findAll();
}
