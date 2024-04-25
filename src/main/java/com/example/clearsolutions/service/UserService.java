package com.example.clearsolutions.service;

import com.example.clearsolutions.entity.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    void createUser(User user);
    User updateUser(User user,Long id);
    void deleteUser(Long id);
    List<User> searchUsersByBirthDateRange(Date fromDate, Date toDate);
}
