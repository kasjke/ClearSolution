package com.example.clearsolutions.repository;

import com.example.clearsolutions.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBirthdayBetween(Date fromDate, Date toDate);
}
