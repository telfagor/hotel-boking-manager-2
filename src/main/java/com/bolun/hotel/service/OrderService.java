package com.bolun.hotel.service;

import com.bolun.hotel.dto.ReadOrderDto;
import com.bolun.hotel.dto.CreateOrderDto;
import com.bolun.hotel.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    CreateOrderDto create(CreateOrderDto createOrderDto);

    List<ReadOrderDto> findAll();

    List<ReadOrderDto> findAllByUserId(Long id);

    Boolean updateStatusByOrderId(Long id, OrderStatus status);
}
