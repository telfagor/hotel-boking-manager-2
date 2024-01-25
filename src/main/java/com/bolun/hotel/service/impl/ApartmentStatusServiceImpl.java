package com.bolun.hotel.service.impl;

import com.bolun.hotel.dao.ApartmentStatusDao;
import com.bolun.hotel.entity.enums.ApartmentStatus;
import com.bolun.hotel.service.ApartmentStatusService;
import com.bolun.hotel.dao.impl.ApartmentStatusDaoImpl;

import java.util.List;

public class ApartmentStatusServiceImpl implements ApartmentStatusService {

    private static final ApartmentStatusService INSTANCE = new ApartmentStatusServiceImpl();
    private final ApartmentStatusDao apartmentStatusDao = ApartmentStatusDaoImpl.getInstance();

    @Override
    public List<ApartmentStatus> findAll() {
        return apartmentStatusDao.findAll();
    }

    public static final ApartmentStatusService getInstance() {
        return INSTANCE;
    }
}
