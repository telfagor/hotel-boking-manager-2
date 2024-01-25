package com.bolun.hotel.helper;

import com.bolun.hotel.entity.User;
import com.bolun.hotel.dao.UserDao;
import com.bolun.hotel.entity.Apartment;
import lombok.experimental.UtilityClass;
import com.bolun.hotel.dao.ApartmentDao;
import com.bolun.hotel.dto.CreateOrderDto;
import com.bolun.hotel.dao.impl.UserDaoImpl;
import com.bolun.hotel.dao.impl.ApartmentDaoImpl;

import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class PriceCalculation {

    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final ApartmentDao apartmentDao = ApartmentDaoImpl.getInstance();

    public static boolean isEnoughMoney(CreateOrderDto createOrderDto) {
        Optional<User> maybeUser = userDao.findById(createOrderDto.readUserDto().getId());
        Optional<Apartment> maybeApartment = apartmentDao.findById(Long.parseLong(createOrderDto.apartmentId()));

        if (maybeUser.isEmpty()) {
            throw new RuntimeException();
        }

        if (maybeApartment.isEmpty()) {
            throw new RuntimeException();
        }

        return getTotalPrice(createOrderDto) <= maybeUser.get().getUserDetail().getMoney();
    }

    public static int getTotalPrice(CreateOrderDto createOrderDto) {
        Optional<User> maybeUser = userDao.findById(createOrderDto.readUserDto().getId());
        Optional<Apartment> maybeApartment = apartmentDao.findById(Long.parseLong(createOrderDto.apartmentId()));
        int totalPrice = 0;

        if (maybeUser.isPresent() && maybeApartment.isPresent()) {
            Apartment apartment = maybeApartment.get();

            LocalDateTime checkIn = LocalDateTimeFormatter.format(createOrderDto.checkIn().concat(":00"));
            LocalDateTime checkOut = LocalDateTimeFormatter.format(createOrderDto.checkOut().concat(":00"));

            long hours = ChronoUnit.HOURS.between(checkIn, checkOut);
            BigDecimal pricePerHour = apartment.getPricePerHour();

            totalPrice = (int) (hours * pricePerHour.intValue());
        }
        return totalPrice;
    }
}
