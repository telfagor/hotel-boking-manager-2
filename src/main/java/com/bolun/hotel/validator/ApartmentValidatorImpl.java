package com.bolun.hotel.validator;

import com.bolun.hotel.dto.CreateApartmentDto;

public class ApartmentValidatorImpl implements Validator<CreateApartmentDto> {

    private static final ApartmentValidatorImpl INSTANCE = new ApartmentValidatorImpl();

    @Override
    public ValidationResult isValid(CreateApartmentDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (object.numberOfRooms() == null || object.numberOfRooms().isBlank()) {
            validationResult.add(Error.of("invalid numbers of rooms", "invalid number of rooms"));
        }

        if (object.numberOfSeats() == null || object.numberOfSeats().isBlank()) {
            validationResult.add(Error.of("invalid number of seats", "invalid number of seats"));
        }

        if (object.pricePerHour() == null || object.pricePerHour().isBlank()) {
            validationResult.add(Error.of("invalid price per hour", "invalid price per hour"));
        }

        if (object.photo() == null) {
            validationResult.add(Error.of("photo is missing", "photo is missing"));
        }

        if (object.type() == null) {
            validationResult.add(Error.of("type is missing", "type is missing"));
        }

        return validationResult;
    }

    public static ApartmentValidatorImpl getInstance() {
        return INSTANCE;
    }
}
