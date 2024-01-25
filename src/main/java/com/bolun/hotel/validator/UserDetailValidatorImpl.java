package com.bolun.hotel.validator;

import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.helper.AgeCalculation;
import com.bolun.hotel.helper.LocalDateFormatter;

import java.time.Month;
import java.time.LocalDate;

public class UserDetailValidatorImpl implements Validator<UserDetailDto> {

    private static final UserDetailValidatorImpl INSTANCE = new UserDetailValidatorImpl();
    private static final LocalDate MIN_BIRTH_DATE = LocalDate.of(1950, Month.JANUARY, 1);

    @Override
    public ValidationResult isValid(UserDetailDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (object.contactNumber() == null || object.contactNumber().isBlank()) {
            validationResult.add(Error.of("invalid.contact_number", "invalid contact number"));
        }

        if (!LocalDateFormatter.isValid(object.birthdate())) {
            validationResult.add(Error.of("invalid.birthdate", "invalid birthdate"));
        }

        if (LocalDateFormatter.format(object.birthdate()).isBefore(MIN_BIRTH_DATE)) {
            validationResult.add(Error.of("invalid.birthdate", "The date should be after " + MIN_BIRTH_DATE));
        }

        if (!AgeCalculation.isAdult(object.birthdate())) {
            validationResult.add(Error.of("Only adults can rend apartments", "invalid age"));
        }

        if (object.money() == null || object.money().equals("0")) {
            validationResult.add(Error.of("invalid.money", "insufficient money"));
        }

        return validationResult;
    }

    public static UserDetailValidatorImpl getInstance() {
        return INSTANCE;
    }
}
