package com.mailindra.demo.simpleUserManagement.service;

import com.mailindra.demo.simpleUserManagement.SimpleUserManagementApplication;
import com.mailindra.demo.simpleUserManagement.controller.dto.UserDto;
import com.mailindra.demo.simpleUserManagement.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SimpleUserManagementApplication.class})

public class UserServiceIntegrationTest {
    @Autowired
    UserService userService;

    @Test
    void testCreateUser_Success(){
        UserDto userDto = new UserDto("Ronny","11","2000-01-01");
        User createdUser = userService.createUser(userDto);
        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals("00011", createdUser.getSsn());
        Assertions.assertEquals(2000, createdUser.getDob().getYear());
        Assertions.assertEquals("SPRING_BOOT_TEST", createdUser.getCreatedBy());
        Assertions.assertFalse(createdUser.getIsDeleted());
    }


}
