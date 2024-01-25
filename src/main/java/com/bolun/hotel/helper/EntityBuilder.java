package com.bolun.hotel.helper;

import lombok.SneakyThrows;
import com.bolun.hotel.entity.User;
import com.bolun.hotel.entity.Order;
import com.bolun.hotel.entity.UserDetail;
import com.bolun.hotel.entity.enums.*;
import lombok.experimental.UtilityClass;
import com.bolun.hotel.entity.Apartment;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Optional;
import java.math.BigDecimal;

@UtilityClass
public class EntityBuilder {

    private static final String ID = "id";
    private static final String NUMBER_OF_ROOMS = "number_of_rooms";
    private static final String NUMBER_OF_SEATS = "number_of_seats";
    private static final String PRICE_PER_HOUR = "price_per_hour";
    private static final String PHOTO = "photo";
    private static final String STATUS = "status";
    private static final String ORDER_STATUS = "order_status";
    private static final String TYPE = "apartment_type";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String USER_PASSWORD = "user_password";
    private static final String ROLE = "user_role";
    private static final String GENDER = "gender";
    private static final String USER_ID = "user_id";
    private static final String USER_DETAIL_ID = "user_detail_id";
    private static final String CONTACT_NUMBER = "contact_number";
    private static final String USER_PHOTO = "user_photo";
    private static final String BIRTHDATE = "birthdate";
    private static final String MONEY = "money";
    private static final String CHECK_IN = "check_in";
    private static final String CHECK_OUT = "check_out";

    @SneakyThrows
    public static Order buildOrder(ResultSet resultSet) {
        return Order.builder()
                .id(resultSet.getLong(ID))
                .checkIn(resultSet.getTimestamp(CHECK_IN).toLocalDateTime())
                .checkOut(resultSet.getTimestamp(CHECK_OUT).toLocalDateTime())
                .user(buildUser(resultSet))
                .apartment(buildApartment(resultSet))
                .status(OrderStatus.valueOf(resultSet.getObject(ORDER_STATUS, String.class)))
                .build();
    }

    @SneakyThrows
    public static Apartment buildApartment(ResultSet resultSet) {
        return Apartment.builder()
                .id(resultSet.getObject(ID, Long.class))
                .numberOfRooms(resultSet.getObject(NUMBER_OF_ROOMS, Integer.class))
                .numberOfSeats(resultSet.getObject(NUMBER_OF_SEATS, Integer.class))
                .pricePerHour(resultSet.getObject(PRICE_PER_HOUR, BigDecimal.class))
                .photo(resultSet.getObject(PHOTO, String.class))
                .type(ApartmentType.valueOf(resultSet.getObject(TYPE, String.class)))
                .status(ApartmentStatus.valueOf(resultSet.getObject(STATUS, String.class)))
                .build();
    }



    @SneakyThrows
    public User buildUser(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getObject(USER_ID, Long.class))
                .firstName(resultSet.getObject(FIRST_NAME, String.class))
                .lastName(resultSet.getObject(LAST_NAME, String.class))
                .email(resultSet.getObject(EMAIL, String.class))
                .password(resultSet.getObject(USER_PASSWORD, String.class))
                .role(Role.valueOf(resultSet.getObject(ROLE, String.class)))
                .gender(Gender.valueOf(resultSet.getObject(GENDER, String.class)))
                .userDetail(buildUserDetail(resultSet).orElse(null))
                .build();
    }

    @SneakyThrows
    public Optional<UserDetail> buildUserDetail(ResultSet resultSet) {
        if (resultSet.getObject(USER_DETAIL_ID, Long.class) != null) {
            return Optional.of(UserDetail.builder()
                    .id(resultSet.getObject(USER_DETAIL_ID, Long.class))
                    .contactNumber(resultSet.getObject(CONTACT_NUMBER, String.class))
                    .photo(resultSet.getObject(USER_PHOTO, String.class))
                    .birthdate(resultSet.getObject(BIRTHDATE, Date.class).toLocalDate())
                    .money(resultSet.getObject(MONEY, Integer.class))
                    .build());
        }

        return Optional.empty();
    }
}

