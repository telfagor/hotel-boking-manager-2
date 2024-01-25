package com.bolun.hotel.dao.impl;

import lombok.NoArgsConstructor;
import com.bolun.hotel.dao.UserDetailDao;
import com.bolun.hotel.entity.UserDetail;
import com.bolun.hotel.helper.EntityBuilder;
import com.bolun.hotel.exception.DaoException;
import com.bolun.hotel.connection.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDetailDaoImpl implements UserDetailDao {
    private static final UserDetailDao INSTANCE = new UserDetailDaoImpl();
    private static final String ID = "id";
    private static final String PHOTO = "photo";

    private static final String INSERT_SQL = """
            INSERT INTO user_detail (id, contact_number, photo, birthdate, money)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE_SQL = """
            UPDATE user_detail
            SET contact_number = ?,
                photo = ?,
                birthdate = ?,
                money = ?
            WHERE id = ?;
            """;

    private static final String UPDATE_USER_MONEY = """
            UPDATE user_detail
            SET money = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id AS user_detail_id,
                   contact_number,
                   photo AS user_photo,
                   birthdate,
                   money
            FROM user_detail
            WHERE id = ?
            """;


    private static final String FIND_USERS_IMAGES = """
            SELECT photo
            FROM user_detail
            """;

    private static final String FIND_USER_IMAGE_BY_ID = FIND_USERS_IMAGES + " WHERE id = ?";

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM user_detail
            WHERE id = ?
            """;


    @Override
    public UserDetail save(UserDetail userDetail) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, userDetail.getId());
            preparedStatement.setString(2, userDetail.getContactNumber());
            preparedStatement.setObject(3, userDetail.getPhoto());
            preparedStatement.setDate(4, Date.valueOf(userDetail.getBirthdate()));
            preparedStatement.setInt(5, userDetail.getMoney());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            userDetail.setId(resultSet.getLong(ID));

            return userDetail;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }


    @Override
    public boolean update(UserDetail userDetail) {
        String photo = userDetail.getPhoto().isEmpty()
                ? findUserImageByUserId(userDetail.getId()).orElse(null)
                : userDetail.getPhoto();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, userDetail.getContactNumber());
            preparedStatement.setObject(2, photo);
            preparedStatement.setDate(3, Date.valueOf(userDetail.getBirthdate()));
            preparedStatement.setInt(4, userDetail.getMoney());
            preparedStatement.setLong(5, userDetail.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateUserMoney(Long userId, int money) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_MONEY)) {
            preparedStatement.setInt(1, money);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<UserDetail> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDetail userDetail = null;

            if (resultSet.next()) {
                userDetail = EntityBuilder.buildUserDetail(resultSet).orElse(null);
            }

            return Optional.ofNullable(userDetail);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    @Override
    public Optional<String> findUserImageByUserId(Long userId) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_IMAGE_BY_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(resultSet.getObject(PHOTO, String.class));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<String> findAllUsersPhotos() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_IMAGES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> photos = new ArrayList<>();
            while (resultSet.next()) {
                photos.add(resultSet.getObject("photo", String.class));
            }

            return photos;
        } catch (SQLException ex) {
            throw new DaoException("message", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage(), ex);
        }
    }

    public static UserDetailDao getInstance() {
        return INSTANCE;
    }
}
