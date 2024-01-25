package com.bolun.hotel.dao;

import java.util.List;
import com.bolun.hotel.entity.enums.Role;

public interface RoleDao {

    List<Role> findAll();
}
