package com.bolun.hotel.dao;

import java.util.List;
import com.bolun.hotel.entity.enums.ApartmentType;

public interface ApartmentTypeDao {

    List<ApartmentType> findAll();
}
