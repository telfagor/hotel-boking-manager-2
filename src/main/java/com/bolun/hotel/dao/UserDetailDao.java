package com.bolun.hotel.dao;

import com.bolun.hotel.entity.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserDetailDao extends BaseDao<Long, UserDetail> {

   Optional<String> findUserImageByUserId(Long id);

   List<String> findAllUsersPhotos();
}
