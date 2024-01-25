package com.bolun.hotel.dao;

import java.util.List;
import com.bolun.hotel.entity.Apartment;

public interface ApartmentDao extends BaseDao<Long, Apartment> {

    List<Apartment> findAll();

    List<String> findAllImagesPaths();
}
