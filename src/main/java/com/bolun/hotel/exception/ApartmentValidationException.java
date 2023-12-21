package com.bolun.hotel.exception;

import lombok.Getter;

import java.util.List;
import com.bolun.hotel.validator.Error;

public class ApartmentValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;

    public ApartmentValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
