package com.bolun.hotel.validator;

import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.helper.LocalDateFormatter;

public class UpdateUserDetailValidator implements Validator<UserDetailDto> {

    @Override
    public ValidationResult isValid(UserDetailDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (object.userId() == null) {
            validationResult.add(Error.of("id was not settled", "id was not settled"));
        }

        if (object.contactNumber() == null || object.contactNumber().isBlank()) {
            validationResult.add(Error.of("contact number cannot be empty", "contact number cannot be empty"));
        }

        if (!LocalDateFormatter.isValid(object.birthdate())) {
            validationResult.add(Error.of("invalid.birthdate", "Birthdate is invalid"));
        }

        if (object.money() == null || object.money().equals("0")) {
            validationResult.add(Error.of("invalid.money", "The money cannot be less than 0"));
        }

        return validationResult;
    }
}
