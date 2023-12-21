package com.bolun.hotel.exception;

import lombok.Getter;
import java.util.List;
import com.bolun.hotel.validator.Error;

public class UserDetailValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;

    public UserDetailValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
