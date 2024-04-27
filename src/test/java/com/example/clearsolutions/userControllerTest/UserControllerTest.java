package com.example.clearsolutions.userControllerTest;

import com.example.clearsolutions.controller.UserController;
import com.example.clearsolutions.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.clearsolutions.constantsTest.ConstantsTest.USER_CONTROLLER_TEST_NAME;
import static com.example.clearsolutions.constantsTest.ConstantsTest.USER_INFO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName(USER_CONTROLLER_TEST_NAME + " addUser")
    void testAddUser() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_INFO))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName(USER_CONTROLLER_TEST_NAME + " UpdateUser")
    void testUpdateUser() throws Exception {
        Long userId = 1L;

        mockMvc.perform(put("/api/v1/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_INFO))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName(USER_CONTROLLER_TEST_NAME + " DeleteUser")
    void testDeleteUser() throws Exception {
        Long userId = 1L;

        mockMvc.perform(delete("/api/v1/users/{id}", userId))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName(USER_CONTROLLER_TEST_NAME + " testSearchUsersByBirthDateRange")
    void testSearchUsersByBirthDateRange() throws Exception {


        mockMvc.perform(get("/api/v1/users/search-by-date")
                        .param("fromDate", "2024-04-27")
                        .param("toDate", "2024-04-28"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}
