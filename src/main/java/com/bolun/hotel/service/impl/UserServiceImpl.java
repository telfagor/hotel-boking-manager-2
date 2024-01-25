package com.bolun.hotel.service.impl;

import lombok.NoArgsConstructor;
import com.bolun.hotel.entity.User;
import com.bolun.hotel.dao.UserDao;
import com.bolun.hotel.dto.UserFilter;
import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.dto.CreateUserDto;
import com.bolun.hotel.dao.impl.UserDaoImpl;
import com.bolun.hotel.service.UserService;
import com.bolun.hotel.validator.ValidationResult;
import com.bolun.hotel.mapper.impl.ReadUserDtoMapper;
import com.bolun.hotel.exception.UserNotValidException;
import com.bolun.hotel.mapper.impl.CreateUserDtoMapper;
import com.bolun.hotel.validator.RegistrationValidatorImpl;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

//TODO: Maybe return DTO
@NoArgsConstructor(access = PRIVATE)
public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final CreateUserDtoMapper createUserDtoMapper = CreateUserDtoMapper.getInstance();
    private final ReadUserDtoMapper readUserDtoMapper = ReadUserDtoMapper.getInstance();
    private final RegistrationValidatorImpl validator = RegistrationValidatorImpl.getInstance();

    @Override
    public ReadUserDto save(CreateUserDto createUserDto) {
        ValidationResult validationResult = validator.isValid(createUserDto);

        if (validationResult.hasErrors()) {
            throw new UserNotValidException(validationResult.getErrors());
        }
        User user = createUserDtoMapper.mapFrom(createUserDto);
        User savedUser = userDao.save(user);
        return readUserDtoMapper.mapFrom(savedUser);
    }

    @Override
    public Optional<ReadUserDto> findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(readUserDtoMapper::mapFrom);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean isEmailAlreadyExist(String email) {
        return userDao.isEmailAlreadySaved(email);
    }

    //TODO: Do I need this method? The user cannot update this information
    @Override
    public Boolean update(CreateUserDto createUserDto) {
        User user = createUserDtoMapper.mapFrom(createUserDto);
        return userDao.update(user);
    }

    public List<ReadUserDto> findAll() {
        return userDao.findAll().stream()
                .map(readUserDtoMapper::mapFrom)
                .toList();
    }

    @Override
    public List<ReadUserDto> findAll(UserFilter userFilter) throws IllegalAccessException {
        return userDao.findAll(userFilter).stream()
                .map(readUserDtoMapper::mapFrom)
                .toList();
    }

    @Override
    public Boolean deleteById(Long id) {
        return userDao.delete(id);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
