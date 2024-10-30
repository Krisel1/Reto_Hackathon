package com.hackathon.bankingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.bankingapp.models.ERole;
import com.hackathon.bankingapp.models.User;
import com.hackathon.bankingapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private User user1;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user1 = new User(1L, "Fran", "user1@example.com", "password1", "Spain", 624858987, "1234567890", "1234", ERole.USER);
    }


//    @Test
//    void test_Create_User() throws Exception {
//        User user1 = new User(1L, "Fran", "user1@example.com", "password1", "Spain", 624858987, "1234567890", "1234", ERole.USER);
//
//        when(userService.createUser(any(User.class))).thenReturn(user1);
//
//        String userJson = "{\"username\":\"Fran\",\"email\":\"user1@example.com\",\"password\":\"password1\",\"address\":\"Spain\",\"phoneNumber\":624858987,\"accountNumber\":\"1234567890\",\"pin\":\"1234\",\"role\":\"USER\"}";
//
//        mockMvc.perform(post("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userJson))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.username").value("Fran"))
//                .andExpect(jsonPath("$.email").value("user1@example.com"))
//                .andExpect(jsonPath("$.address").value("Spain"))
//                .andExpect(jsonPath("$.phoneNumber").value(624858987))
//                .andExpect(jsonPath("$.accountNumber").value("1234567890"))
//                .andExpect(jsonPath("$.pin").value("1234"))
//                .andExpect(jsonPath("$.role").value("USER"));
//
//        verify(userService, times(1)).createUser(any(User.class));
//    }


    @Test
    void Test_Get_All_User() throws Exception {

        when(userService.getAllUsers()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).getAllUsers();
    }


    @Test
    void getUserById() throws Exception {
        User user1 = new User(1L, "Valen", "user2@example.com", "password2", "Spain", 123456789, "1234567890", "1234", ERole.USER);
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user1));

        String userJson = new ObjectMapper().writeValueAsString(user1);

        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));
    }

    @Test
    public void test_Update_User() {
        Long id = 1L;
        User user = user1 = new User(1L, "Sofia", "user2@example.com", "password2", "Spain", 987456321, "1234567890", "1234", ERole.USER);
        user.setId(id);

        userService.updateUser(user);

        verify(userService, times(1)).updateUser(user);
    }

    @Test
    void delete_User_By_Id() throws Exception {
        long UserId = 1L;

        mockMvc.perform(delete("/api/users/{id}", UserId))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(UserId);
    }

}