package com.bolun.hotel.service.impl;

import com.bolun.hotel.dao.ApartmentTypeDao;
import com.bolun.hotel.dao.impl.ApartmentTypeDaoImpl;
import com.bolun.hotel.entity.enums.ApartmentType;
import com.bolun.hotel.service.ApartmentTypeService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApartmentTypeServiceImpl implements ApartmentTypeService {

    private static final ApartmentTypeService INSTANCE = new ApartmentTypeServiceImpl();
    private static final ApartmentTypeDao apartmentTypeDao = ApartmentTypeDaoImpl.getInstance();

    @Override
    public List<ApartmentType> findAll() {
        return apartmentTypeDao.findAll();
    }

    public static ApartmentTypeService getInstance() {
        return INSTANCE;
    }
}
