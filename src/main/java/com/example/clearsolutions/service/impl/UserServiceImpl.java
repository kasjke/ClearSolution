package com.example.clearsolutions.service.impl;

import com.example.clearsolutions.entity.User;
import com.example.clearsolutions.exception.IllStateException;
import com.example.clearsolutions.exception.InvalidDateRangeException;
import com.example.clearsolutions.exception.NotFoundException;
import com.example.clearsolutions.repository.UserRepository;
import com.example.clearsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${age}")
    private Integer UNDERAGE;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        if (!allowedToCreate(user.getBirthday())) {
            throw new IllStateException();
        }
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
        userRepository.save(newUser);
    }

    @Override
    public User updateUser(User user, Long id) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getBirthday() != null && allowedToCreate(user.getBirthday())) {
            oldUser.setBirthday(user.getBirthday());
        }
        if (user.getAddress() != null) {
            oldUser.setAddress(user.getAddress());
        }
        if (user.getPhoneNumber() != null) {
            oldUser.setPhoneNumber(user.getPhoneNumber());
        }
        userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        userRepository.deleteById(user.getId());
    }

    @Override
    public List<User> searchUsersByBirthDateRange(Date fromDate, Date toDate) {
        if (fromDate.after(toDate)) {
            throw new InvalidDateRangeException();
        }
        return userRepository.findByBirthdayBetween(fromDate, toDate);
    }

    private boolean allowedToCreate(Date birthday) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -UNDERAGE);
        return birthday.before(now.getTime());
    }
}
