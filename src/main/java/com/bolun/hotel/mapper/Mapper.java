package com.bolun.hotel.mapper;

//TODO: mapToEntity, mapToDto
public interface Mapper<F, T> {

    T mapFrom(F object);
}
