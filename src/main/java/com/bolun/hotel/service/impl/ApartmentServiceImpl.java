package com.bolun.hotel.service.impl;

import com.bolun.hotel.dao.ApartmentDao;
import com.bolun.hotel.dao.OrderDao;
import com.bolun.hotel.dao.impl.ApartmentDaoImpl;
import com.bolun.hotel.dao.impl.OrderDaoImpl;
import com.bolun.hotel.dto.CreateApartmentDto;
import com.bolun.hotel.dto.ReadApartmentDto;
import com.bolun.hotel.entity.Apartment;
import com.bolun.hotel.entity.Order;
import com.bolun.hotel.exception.ApartmentValidationException;
import com.bolun.hotel.mapper.impl.CreateApartmentDtoMapper;
import com.bolun.hotel.service.ApartmentService;
import com.bolun.hotel.service.ImageService;
import com.bolun.hotel.validator.ApartmentValidatorImpl;
import com.bolun.hotel.validator.ValidationResult;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.bolun.hotel.entity.enums.ApartmentStatus.AVAILABLE;
import static com.bolun.hotel.entity.enums.ApartmentStatus.OCCUPIED;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApartmentServiceImpl implements ApartmentService {

    private static final ApartmentService INSTANCE = new ApartmentServiceImpl();
    private static final String IMAGE_PARENT_PATH = "/images/";
    private final ApartmentDao apartmentDao = ApartmentDaoImpl.getInstance();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final CreateApartmentDtoMapper createApartmentDtoMapper = CreateApartmentDtoMapper.getInstance();

    private final ApartmentValidatorImpl apartmentValidator = ApartmentValidatorImpl.getInstance();
    @Override
    public CreateApartmentDto save(CreateApartmentDto createApartmentDto) throws IOException {
        ValidationResult validationResult = apartmentValidator.isValid(createApartmentDto);

        if (validationResult.hasErrors()) {
            throw new ApartmentValidationException(validationResult.getErrors());
        }

        Apartment apartment = createApartmentDtoMapper.mapFrom(createApartmentDto);
        apartmentDao.save(apartment);
        imageService.upload(apartment.getPhoto(), createApartmentDto.photo().getInputStream());

        return createApartmentDto;
    }

    @Override
    public List<ReadApartmentDto> findAll() {
        verifyTheApartmentStatus();

        return apartmentDao.findAll().stream()
                .map(apartment -> new ReadApartmentDto(
                        apartment.getId(),
                        apartment.getNumberOfRooms(),
                        apartment.getNumberOfSeats(),
                        apartment.getPricePerHour(),
                        apartment.getPhoto(),
                        apartment.getStatus().name(),
                        apartment.getType().name()
                ))
                .toList();
    }

    @Override
    public List<String> findAllImagesPaths() {
        return apartmentDao.findAllImagesPaths().stream()
                .map(IMAGE_PARENT_PATH::concat)
                .toList();
    }

    private void verifyTheApartmentStatus() {
        List<Apartment> apartments = apartmentDao.findAll().stream()
                .filter(apartment -> OCCUPIED == apartment.getStatus())
                .toList();

        for (Apartment apartment : apartments) {
            List<Order> orders = orderDao.findByApartmentId(apartment.getId());
            boolean isCheckOutDateTimeBeforeCurrent = true;

            for (Order order : orders) {
                LocalDateTime current = LocalDateTime.now();
                if (order.getCheckOut().isAfter(current) || order.getCheckOut().isEqual(current)) {
                    isCheckOutDateTimeBeforeCurrent = false;
                    break;
                }
            }

            if (isCheckOutDateTimeBeforeCurrent) {
                apartment.setStatus(AVAILABLE);
                apartmentDao.update(apartment);
            }
        }
    }

    public static ApartmentService getInstance() {
        return INSTANCE;
    }
}
