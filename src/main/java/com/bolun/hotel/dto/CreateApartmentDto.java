package com.bolun.hotel.dto;

import jakarta.servlet.http.Part;

public record CreateApartmentDto(String numberOfRooms,
                                 String numberOfSeats,
                                 String pricePerHour,
                                 Part photo,
                                 String status,
                                 String type) {
}
