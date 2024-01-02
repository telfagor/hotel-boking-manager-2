package com.bolun.hotel.service.impl;

import com.bolun.hotel.dao.UserDao;
import com.bolun.hotel.dao.UserDetailDao;
import com.bolun.hotel.dao.impl.UserDaoImpl;
import com.bolun.hotel.dao.impl.UserDetailDaoImpl;
import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.entity.UserDetail;
import com.bolun.hotel.exception.UserDetailValidationException;
import com.bolun.hotel.mapper.impl.UserDetailDtoMapper;
import com.bolun.hotel.service.ImageService;
import com.bolun.hotel.service.UserDetailService;
import com.bolun.hotel.validator.UpdateUserDetailValidator;
import com.bolun.hotel.validator.UserDetailValidatorImpl;
import com.bolun.hotel.validator.ValidationResult;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDetailServiceImpl implements UserDetailService {

    private static final UserDetailService INSTANCE = new UserDetailServiceImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final UserDetailDao userDetailDao = UserDetailDaoImpl.getInstance();

    private final ImageService imageService = ImageService.getInstance();
    private final UserDetailDtoMapper userDetailDtoMapper = UserDetailDtoMapper.getInstance();
    private final UserDetailValidatorImpl userDetailValidatorImpl = UserDetailValidatorImpl.getInstance();

    @SneakyThrows
    @Override
    public UserDetailDto create(UserDetailDto userDetailDto) {
        ValidationResult validationResult = userDetailValidatorImpl.isValid(userDetailDto);

        if (!validationResult.isValid()) { //TODO: hasErrors
            throw new UserDetailValidationException(validationResult.getErrors());
        }

        UserDetail userDetail = userDetailDtoMapper.mapFrom(userDetailDto);
        imageService.upload(userDetail.getPhoto(), userDetailDto.photo().getInputStream());
        UserDetail actualUserDetail =  userDetailDao.save(userDetail);

        userDao.findById(userDetail.getId()).ifPresent(user -> {
            user.setUserDetail(actualUserDetail);
            userDao.update(user);
        });

        return userDetailDto;
    }

    @SneakyThrows
    public UserDetailDto update(UserDetailDto userDetailDto) {
        ValidationResult validationResult = new UpdateUserDetailValidator().isValid(userDetailDto);

        if (!validationResult.isValid()) {
            throw new UserDetailValidationException(validationResult.getErrors());
        }

        UserDetail userDetail = userDetailDtoMapper.mapFrom(userDetailDto);
        if (!userDetail.getPhoto().isBlank()) {
            imageService.upload(userDetail.getPhoto(), userDetailDto.photo().getInputStream());
        }
        userDetailDao.update(userDetail);

        return userDetailDto;
    }


    @Override
    public Optional<UserDetail> findById(Long id) {

        return userDetailDao.findById(id);
    }

    @Override
    public Optional<String> findUserImageByUserId(Long userId) {
        return userDetailDao.findUserImageByUserId(userId);
    }

    public static UserDetailService getInstance() {
        return INSTANCE;
    }
}
