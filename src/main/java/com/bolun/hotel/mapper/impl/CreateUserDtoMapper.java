package com.bolun.hotel.mapper.impl;

import lombok.NoArgsConstructor;
import com.bolun.hotel.entity.User;
import com.bolun.hotel.mapper.Mapper;
import com.bolun.hotel.dto.CreateUserDto;
import com.bolun.hotel.entity.enums.Gender;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserDtoMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserDtoMapper INSTANCE = new CreateUserDtoMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .firstName(object.firstName())
                .lastName(object.lastName())
                .email(object.email())
                .password(object.password())
                .gender(Gender.valueOf(object.gender()))
                .build();
    }

    public static CreateUserDtoMapper getInstance() {
        return INSTANCE;
    }
}
