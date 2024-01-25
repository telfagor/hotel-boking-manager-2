package com.bolun.hotel.dao;


import java.util.List;
import java.util.Optional;
import com.bolun.hotel.entity.UserDetail;

public interface UserDetailDao extends BaseDao<Long, UserDetail> {

   List<String> findAllUsersPhotos();

   Optional<String> findUserImageByUserId(Long id);

   void updateUserMoney(Long userId, int money);
}
