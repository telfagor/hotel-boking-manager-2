package com.bolun.hotel.dao;

import com.bolun.hotel.dto.ReadOrderDto;
import com.bolun.hotel.entity.Order;
import com.bolun.hotel.entity.enums.OrderStatus;

import java.util.List;

public interface OrderDao extends BaseDao<Long, Order> {

    List<Order> findByApartmentId(Long id);

    List<Order> findAll();

    List<Order> findAllByUserId(Long id);

    Boolean updateStatusByOrderId(Long id, OrderStatus status);

//    List<ReadOrderDto> findAll()
}