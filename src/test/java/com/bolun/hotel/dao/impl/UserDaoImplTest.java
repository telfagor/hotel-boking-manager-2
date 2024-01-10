package com.bolun.hotel.dao.impl;

import com.bolun.hotel.dao.UserDao;
import com.bolun.hotel.dao.UserDetailDao;
import com.bolun.hotel.dto.UserFilter;
import com.bolun.hotel.entity.User;
import com.bolun.hotel.entity.UserDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.time.Month;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static com.bolun.hotel.entity.enums.Gender.MALE;
import static com.bolun.hotel.entity.enums.Role.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoImplTest {

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final UserDetailDao userDetailDao = UserDetailDaoImpl.getInstance();
    private final List<User> users = new ArrayList<>();


    @Test
    @DisplayName("save user without user detail")
    void saveWithoutUserDetail() {
        User user = createUser();

        User actualResult = userDao.save(user);
        users.add(user);

        assertNotNull(actualResult.getId());
    }

    @Test
    @DisplayName("save user with user detail")
    void saveWithUserDetail() {
        User user = createUser();
        User actualUser = userDao.save(user);
        UserDetail userDetail = createUserDetail();
        userDetail.setId(actualUser.getId());
        actualUser.setUserDetail(userDetail);
        userDetailDao.save(userDetail);
        userDao.update(actualUser);
        users.add(user);
        assertNotNull(actualUser.getId());
        assertNotNull(actualUser.getUserDetail().getId());
    }

    @Test
    @DisplayName("should update user")
    void update() {
        User user = createUser();
        User actualUser = userDao.save(user);
        users.add(user);
        actualUser.setFirstName("Ion");
        boolean updateResult = userDao.update(actualUser);
        assertTrue(updateResult);
    }

    @Test
    @DisplayName("should find user by userId")
    void shouldFindUserIfIdExists() {
        User user = createUser();
        User savedUser = userDao.save(user);
        Optional<User> actualUser = userDao.findById(user.getId());
        assertThat(actualUser).contains(savedUser);
        users.add(savedUser);
    }

    @Test
    @DisplayName("should return an empty optional if the userId does not exists")
    void shouldReturnAnEmptyOptionalIfTheIdDoesNotExist() {
        Long fakeId = 0L;
        Optional<User> actualUser = userDao.findById(fakeId);
        assertThat(actualUser).isNotPresent();
    }

    @Test
    @DisplayName("should find the user by email and password")
    void shouldFindTheUserByEmailAndPassword() {
        User user = createUser();
        User savedUser = userDao.save(user);
        Optional<User> actualResult = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
        users.add(savedUser);
        assertThat(actualResult).contains(savedUser);
    }

    @Test
    @DisplayName("should return 2 users")
    void findAll() {
        User firstUser = createUser();
        User secondUser = createUser();

        User firstSavedUser = userDao.save(firstUser);
        User secondSavedUser = userDao.save(secondUser);

        users.add(firstSavedUser);
        users.add(secondSavedUser);
        List<User> users = userDao.findAll();

        assertThat(users).hasSize(2)
                .containsExactlyInAnyOrder(firstSavedUser, secondSavedUser);
    }

    @Test
    @DisplayName("find all with filter")
    void findAllWithFilter() throws IllegalAccessException {
        UserFilter userFilter = new UserFilter(null,
                null, null, null, null, 2, 1);

        User firstUser = createUser();
        User secondUser = createUser();
        User thirdUser = createUser();
        User fourthUser = createUser();

        User firstSavedUser = userDao.save(firstUser);
        User secondSavedUser = userDao.save(secondUser);
        User thirdSavedUser = userDao.save(thirdUser);
        User fourthSavedUser = userDao.save(fourthUser);

        List<User> firstUserList = userDao.findAll(userFilter);
        assertThat(firstUserList).hasSize(2)
                .containsExactlyInAnyOrder(firstSavedUser, secondSavedUser);

        userFilter.setPageNumber(2);
        List<User> secondUserList = userDao.findAll(userFilter);
        assertThat(secondUserList).hasSize(2)
                .containsExactlyInAnyOrder(thirdSavedUser, fourthSavedUser);

        users.add(firstSavedUser);
        users.add(secondSavedUser);
        users.add(thirdSavedUser);
        users.add(fourthSavedUser);
    }

    @Test
    @DisplayName("should find by userId")
    void deleteById() {
        User user = createUser();
        User savedUser = userDao.save(user);
        boolean deleteResult = userDao.delete(savedUser.getId());
        assertTrue(deleteResult);
    }

    @AfterEach
    void clearDatabase() {
        for (User user : users) {
            userDao.delete(user.getId());
        }
    }

    private User createUser() {
        return User.builder()
                .firstName("test")
                .lastName("test")
                .email("test" + new Random().nextInt())
                .password("test")
                .role(ADMIN)
                .gender(MALE)
                .build();
    }

    private UserDetail createUserDetail() {
        return UserDetail.builder()
                .contactNumber(String.valueOf(new Random().nextInt()))
                .birthdate(LocalDate.of(14, Month.APRIL, 3))
                .money(1500)
                .build();
    }
}
