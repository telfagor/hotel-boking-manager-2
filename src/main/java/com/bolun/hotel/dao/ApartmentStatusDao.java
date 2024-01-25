package com.bolun.hotel.dao;

import java.util.List;
import com.bolun.hotel.entity.enums.ApartmentStatus;

public interface ApartmentStatusDao {

    List<ApartmentStatus> findAll();
}
