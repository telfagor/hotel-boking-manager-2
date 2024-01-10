package com.bolun.hotel.dto;

import com.bolun.hotel.entity.enums.Role;
import com.bolun.hotel.entity.enums.Gender;

public class UserFilter {

    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Gender gender;
    private int pageSize;
    private int pageNumber;

    public UserFilter(String firstName,
                      String lastName,
                      String email,
                      Role role,
                      Gender gender,
                      int pageSize,
                      int pageNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }
}

