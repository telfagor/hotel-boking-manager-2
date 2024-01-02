package com.bolun.hotel.mapper.impl;

import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.entity.UserDetail;
import com.bolun.hotel.helper.LocalDateFormatter;
import com.bolun.hotel.mapper.Mapper;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDetailDtoMapper implements Mapper<UserDetailDto, UserDetail> {

    private static final String IMAGE_PARENT_FOLDER = "users/";
    private static final UserDetailDtoMapper INSTANCE = new UserDetailDtoMapper();

    @Override
    public UserDetail mapFrom(UserDetailDto object) {
        String photo = object.photo().getSubmittedFileName();

        return UserDetail.builder()
                .id(object.userId())
                .contactNumber(object.contactNumber())
                .birthdate(LocalDateFormatter.format(object.birthdate()))
                .money(Integer.parseInt(object.money()))
                .photo(photo.isBlank() ? photo.strip() : IMAGE_PARENT_FOLDER.concat(photo))
                .build();
    }

    public static UserDetailDtoMapper getInstance() {
        return INSTANCE;
    }
}
