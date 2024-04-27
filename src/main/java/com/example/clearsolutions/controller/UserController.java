package com.example.clearsolutions.controller;

import com.example.clearsolutions.entity.User;
import com.example.clearsolutions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.example.clearsolutions.constants.ValidationConstants.DATE_FORMAT;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController   {
    public final UserService userService;

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
         userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id) {
            User updateUser = userService.updateUser(user,id);
            return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
    }
    @GetMapping("/search-by-date")
    public ResponseEntity<List<User>> searchUsersByBirthDateRange(
            @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) Date fromDate,
            @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) Date toDate) {
        List<User> users = userService.searchUsersByBirthDateRange(fromDate, toDate);
        return ResponseEntity.ok(users);
    }
}
