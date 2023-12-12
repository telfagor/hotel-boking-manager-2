package com.bolun.hotel.dao;

import com.bolun.hotel.entity.enums.ApartmentType;

import java.util.List;

public interface ApartmentTypeDao {

    List<ApartmentType> findAll();
}
