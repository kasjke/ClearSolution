package com.example.clearsolutions.userServiceTest;

import com.example.clearsolutions.controller.UserController;
import com.example.clearsolutions.entity.User;
import com.example.clearsolutions.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static com.example.clearsolutions.constantsTest.ConstantsTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
    }

    @Test
    @DisplayName(USER_SERVICE_TEST_NAME + " addUser")
    void testAddUser() {
        ResponseEntity<Void> actualResponse = userController.addUser(user);
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    @DisplayName(USER_SERVICE_TEST_NAME + " UpdateUser")
    void testUpdateUser() {
        Long userId = 1L;
        ResponseEntity<User> expectedResponse = new ResponseEntity<>(user, HttpStatus.OK);
        when(userService.updateUser(user, userId)).thenReturn(user);
        ResponseEntity<User> actualResponse = userController.updateUser(user, userId);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode(),STATUS_CODE_OK);
        assertNotNull(actualResponse.getBody(), RESPONSE_BODY_NOT_NULL);
        assertEquals(expectedResponse.getBody(), actualResponse.getBody(), RESPONSE_BODY_MATCH);

        verify(userService, times(1)).updateUser(user, userId);
    }


    @Test
    @DisplayName(USER_SERVICE_TEST_NAME + " DeleteUser")
    void testDeleteUser() {
        Long userId = 1L;
        ResponseEntity<Void> expectedResponse = new ResponseEntity<>(HttpStatus.OK);

        doNothing().when(userService).deleteUser(userId);
        ResponseEntity<Void> actualResponse = userController.deleteUser(userId);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    @DisplayName(USER_SERVICE_TEST_NAME + " testSearchUsersByBirthDateRange")
    void testSearchUsersByBirthDateRange() {
        Date fromDate = new Date();
        Date toDate = new Date();
        List<User> users = List.of(new User());
        ResponseEntity<List<User>> expectedResponse = new ResponseEntity<>(users, HttpStatus.OK);

        when(userService.searchUsersByBirthDateRange(fromDate, toDate)).thenReturn(users);
        ResponseEntity<List<User>> actualResponse = userController.searchUsersByBirthDateRange(fromDate, toDate);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        verify(userService, times(1)).searchUsersByBirthDateRange(fromDate, toDate);
    }
}
