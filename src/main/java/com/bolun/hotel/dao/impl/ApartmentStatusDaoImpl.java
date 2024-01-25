package com.bolun.hotel.dao.impl;

import lombok.NoArgsConstructor;
import com.bolun.hotel.dao.ApartmentStatusDao;
import com.bolun.hotel.entity.enums.ApartmentStatus;
import com.bolun.hotel.connection.ConnectionManager;
import com.bolun.hotel.exception.DaoException;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApartmentStatusDaoImpl implements ApartmentStatusDao {

    private static final ApartmentStatusDao INSTANCE = new ApartmentStatusDaoImpl();
    private static final String APARTMENT_STATUS = "ap_status";

    private static final String FIND_ALL_SQL = """
            SELECT ap_status
            FROM apartment_status
            """;

    @Override
    public List<ApartmentStatus> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ApartmentStatus> apartmentStatuses = new ArrayList<>();
            while (resultSet.next()) {
                apartmentStatuses.add(ApartmentStatus.valueOf(resultSet.getString(APARTMENT_STATUS)));
            }

            return apartmentStatuses;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    public static final ApartmentStatusDao getInstance() {
        return INSTANCE;
    }
}
