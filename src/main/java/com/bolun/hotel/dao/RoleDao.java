package com.bolun.hotel.dao;

import com.bolun.hotel.entity.enums.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();
}
