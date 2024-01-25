package com.bolun.hotel.service;

import com.bolun.hotel.entity.User;
import com.bolun.hotel.dto.UserFilter;
import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.dto.CreateUserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ReadUserDto save(CreateUserDto createUserDto);

    Optional<ReadUserDto> findByEmailAndPassword(String email, String password);

    Boolean update(CreateUserDto createUserDto);

    Optional<User> findById(Long id);

    boolean isEmailAlreadyExist(String email);

    Boolean deleteById(Long id);
    List<ReadUserDto> findAll();
    List<ReadUserDto> findAll(UserFilter userFilter) throws IllegalAccessException;
}
