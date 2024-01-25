package com.bolun.hotel.dto;

public record CreateUserDto(String firstName,
                            String lastName,
                            String email,
                            String password,
                            String role,
                            String gender) {
}
