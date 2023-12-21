package com.bolun.hotel.validator;

import lombok.NoArgsConstructor;
import com.bolun.hotel.dto.CreateUserDto;
import com.bolun.hotel.entity.enums.Gender;
import com.bolun.hotel.service.UserService;
import com.bolun.hotel.service.impl.UserServiceImpl;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RegistrationValidatorImpl implements Validator<CreateUserDto> {

    private static final RegistrationValidatorImpl INSTANCE = new RegistrationValidatorImpl();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public ValidationResult isValid(CreateUserDto createUserDto) {
        ValidationResult validationResult = new ValidationResult();

        if (createUserDto.firstName() == null || createUserDto.firstName().isBlank()) {
            validationResult.add(Error.of("invalid.first_name", "invalid first name"));
        }

        if (createUserDto.lastName() == null || createUserDto.lastName().isBlank()) {
            validationResult.add(Error.of("invalid.last_name", "invalid last name"));
        }

        if (createUserDto.email() == null || createUserDto.email().isBlank()) {
            validationResult.add(Error.of("invalid.email", "invalid email"));
        }

        /*if (Boolean.TRUE.equals(userService.isEmailAlreadyExist(createUserDto.email()))) {
            validationResult.add(Error.of("invalid.email", "email already exist!"));
        }*/

        if (createUserDto.password() == null || createUserDto.password().isBlank()) {
            validationResult.add(Error.of("invalid.password", "invalid password"));
        }

        if (Gender.find(createUserDto.gender()).isEmpty()) {
            validationResult.add(Error.of("invalid.gender", "invalid gender"));
        }

        return validationResult;
    }

    public static RegistrationValidatorImpl getInstance() {
        return INSTANCE;
    }
}
