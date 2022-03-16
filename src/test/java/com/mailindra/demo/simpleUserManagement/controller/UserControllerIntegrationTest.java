package com.mailindra.demo.simpleUserManagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailindra.demo.simpleUserManagement.controller.dto.UserDto;
import com.mailindra.demo.simpleUserManagement.controller.dto.UserUpdateDto;
import com.mailindra.demo.simpleUserManagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerIntegrationTest {
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCreateUser_success() throws Exception{
        UserDto userDto =  new UserDto("Testing User", "19","2022-03-12");
        mockMvc
                .perform(
                        post("/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto))
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdateUser_success() throws Exception{
        UserUpdateDto dto =  new UserUpdateDto("Testing User","2022-03-12");
        mockMvc
                .perform(
                        put("/v1/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUser_success() throws Exception{
        mockMvc.perform(
                delete("/v1/users/1")
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFestUser_success() throws Exception{
        mockMvc.perform(
                        get("/v1/users/1")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}
