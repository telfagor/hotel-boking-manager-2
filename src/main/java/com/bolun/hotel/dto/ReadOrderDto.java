package com.bolun.hotel.dto;

import lombok.Value;
import lombok.Builder;
import com.bolun.hotel.entity.enums.Gender;
import com.bolun.hotel.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
@Builder
public class ReadOrderDto {
    Long orderId;
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    String firstName;
    String lastName;
    String email;
    Gender gender;
    OrderStatus status;

    @Override
    public String toString() {
        return "Order id: " + orderId +
                "\tcheck in: " + checkIn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "\tcheck out: " + checkOut.format(DateTimeFormatter.ofPattern("yyyy-MM-ss HH:mm:ss")) +
                "\tfirst name: " + firstName +
                "\tlast name: " + lastName +
                "\temail: " + email +
                "\tgender: " + gender +
                "\tstatus: " + status;
    }
}
