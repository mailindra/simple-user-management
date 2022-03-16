package com.mailindra.demo.simpleUserManagement.repository;


import com.mailindra.demo.simpleUserManagement.SimpleUserManagementApplication;
import com.mailindra.demo.simpleUserManagement.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SimpleUserManagementApplication.class})
public class UserRepositoryIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void createUser_success(){
        String defaultCreator ="SPRING_BOOT_TEST";
        User testingUser = User.builder()
                .name("Testing User")
                .ssn("12345")
                .dob(LocalDate.parse("2022-03-12"))
                .createdBy(defaultCreator)
                .updatedBy(defaultCreator)
                .isDeleted(Boolean.FALSE)
                .build();
        User createdUser = userRepository.saveAndFlush(testingUser);
        Assertions.assertNotNull(createdUser.getId());
    }

    @Test
    public void findUserBySsn_success(){
        createUser_success();
        Optional<User> foundUser = userRepository.findBySsnAndIsDeleted("12345",Boolean.FALSE);
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("Testing User", foundUser.get().getName());


    }

}
