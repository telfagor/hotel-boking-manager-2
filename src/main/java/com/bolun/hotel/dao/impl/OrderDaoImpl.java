package com.bolun.hotel.dao.impl;

import lombok.NoArgsConstructor;
import com.bolun.hotel.entity.Order;
import com.bolun.hotel.dao.OrderDao;
import com.bolun.hotel.entity.enums.OrderStatus;
import com.bolun.hotel.exception.DaoException;
import com.bolun.hotel.helper.EntityBuilder;
import com.bolun.hotel.connection.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

//TODO: Add Logger
@NoArgsConstructor(access = PRIVATE)
public class OrderDaoImpl implements OrderDao {

    private static final OrderDao INSTANCE = new OrderDaoImpl();
    private static final String ID = "id";


    private static final String INSERT_SQL = """
            INSERT INTO "order" (check_in, check_out, user_id, order_status_id, apartment_id) 
            VALUES (?, ?, ?, ?, ?) 
            """;

    private static final String UPDATE_SQL = """
            UPDATE "order"
            SET check_in = ?,
                check_out = ?,
                user_id = ?,
                order_status_id = ?,
                apartment_id = ?
            WHERE id = ?
            """;

    private static final String UPDATE_ORDER_STATUS_BY_ID = """
            UPDATE "order"
            SET order_status_id = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT o.id,
                   o.check_in,
                   o.check_out,
                   o.user_id,
                   o.order_status_id,
                   o.apartment_id,
                   os.status AS order_status,          
                   u.id,
                   u.first_name,
                   u.last_name,
                   u.email,
                   u.user_password,
                   u.role_id,
                   u.gender_id,
                   u.user_detail_id,
                   us.id,
                   us.contact_number,
                   us.photo AS user_photo,
                   us.birthdate,
                   us.money,
                   g.gender_type AS gender,
                   r.user_role,
                   a.id,
                   a.number_of_rooms,
                   a.number_of_seats,
                   a.price_per_hour,
                   a.photo,
                   ap_status AS status,
                   ap_type AS apartment_type,
                   aps.id,
                   aps.ap_status,
                   ap.id,
                   ap.ap_type
            FROM "order" o JOIN order_status os
            ON o.order_status_id = os.id JOIN apartment a
            ON o.apartment_id = a.id JOIN apartment_status aps
            ON a.apartment_status_id = aps.id JOIN apartment_type ap
            ON a.apartment_type_id = ap.id JOIN "user" u
            ON o.user_id = u.id JOIN gender g
            ON u.gender_id = g.id JOIN "role" r
            ON u.role_id = r.id LEFT JOIN user_detail us
            ON u.user_detail_id = us.id
            """;

    private static final String FIND_BY_ID = FIND_ALL_SQL + " WHERE o.id = ?";
    private static final String FIND_ORDERS_BY_APARTMENT_ID = FIND_ALL_SQL + " WHERE a.id = ?";
    private static final String FIND_ORDERS_BY_USER_ID = FIND_ALL_SQL + " WHERE u.id = ?";

    private static final String ORDER_BY_ORDER_ID = " ORDER BY o.id";
    private static final String DELETE_BY_ID = """
            DELETE FROM "order"
            WHERE id = ?
            """;


    @Override
    public Order save(Order order) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(order.getCheckIn()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getCheckOut()));
            preparedStatement.setLong(3, order.getUser().getId());
            preparedStatement.setLong(4, order.getStatus().getValue());
            preparedStatement.setLong(5, order.getApartment().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            order.setId(resultSet.getLong(ID));

            return order;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean update(Order order) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(order.getCheckIn()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getCheckOut()));
            preparedStatement.setLong(3, order.getUser().getId());
            preparedStatement.setLong(4, order.getStatus().getValue());
            preparedStatement.setLong(5, order.getApartment().getId());
            preparedStatement.setLong(6, order.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            Order order = null;
            while (resultSet.next()) {
                order = EntityBuilder.buildOrder(resultSet);
            }

            return Optional.ofNullable(order);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }



    @Override
    public List<Order> findByApartmentId(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_APARTMENT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }

            return orders;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    public List<Order> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL + ORDER_BY_ORDER_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }

            return orders;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Order> findAllByUserId(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID + ORDER_BY_ORDER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(EntityBuilder.buildOrder(resultSet));
            }

            return orders;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean updateStatusByOrderId(Long id, OrderStatus status) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_BY_ID)) {
            preparedStatement.setInt(1, status.getValue());
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
