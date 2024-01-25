package com.bolun.hotel.service;

import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.entity.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {

    UserDetailDto create(UserDetailDto userDetailDto);

    UserDetailDto update(UserDetailDto updateUserDetailDto);

    Optional<UserDetail> findById(Long id);

    void updateUserMoney(Long userId, int money);

    Optional<String> findUserImageByUserId(Long userId);

    List<String> findAllUsersImages();
}
