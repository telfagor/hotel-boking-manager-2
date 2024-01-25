package com.bolun.hotel.dao;

import java.util.List;
import com.bolun.hotel.entity.enums.Gender;

public interface GenderDao {

    List<Gender> findAll();
}
