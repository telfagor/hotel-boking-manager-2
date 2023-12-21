package com.bolun.hotel.dao;

import com.bolun.hotel.entity.enums.Gender;

import java.util.List;

public interface GenderDao {

    List<Gender> findAll();
}
