package com.mailindra.demo.simpleUserManagement.repository;


import com.mailindra.demo.simpleUserManagement.SimpleUserManagementApplication;
import com.mailindra.demo.simpleUserManagement.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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

    User savedUser;

   @BeforeEach
    void setup() {
        String defaultCreator ="SPRING_BOOT_TEST";
        User testingUser = User.builder()
                .name("Testing User")
                .ssn("12345")
                .dob(LocalDate.parse("2022-03-12"))
                .createdBy(defaultCreator)
                .updatedBy(defaultCreator)
                .isDeleted(Boolean.FALSE)
                .build();
       savedUser = userRepository.saveAndFlush(testingUser);
    }

    @AfterEach
    void cleanup(){
       userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void createUser_success(){
        String defaultCreator ="SPRING_BOOT_TEST";
        User testingUser = User.builder()
                .name("Testing User")
                .ssn("00012")
                .dob(LocalDate.parse("2022-03-12"))
                .createdBy(defaultCreator)
                .updatedBy(defaultCreator)
                .isDeleted(Boolean.FALSE)
                .build();
        User createdUser = userRepository.saveAndFlush(testingUser);
        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals("Testing User", createdUser.getName());
        Assertions.assertEquals("00012", createdUser.getSsn());

    }

    @Test
    public void findUserBySsn_success(){
        Optional<User> foundUser = userRepository.findBySsnAndIsDeleted(savedUser.getSsn(),Boolean.FALSE);
        Assertions.assertTrue(foundUser.isPresent());
        User user = foundUser.get();
        Assertions.assertEquals(savedUser.getName(), user.getName());
        Assertions.assertEquals(savedUser.getSsn(),user.getSsn());
    }

    @Test
    public void findActiveUser_success(){
       Optional<User> foundUser = userRepository.findByIdAndIsDeleted(savedUser.getId(), Boolean.FALSE);
       Assertions.assertTrue(foundUser.isPresent());
       User user = foundUser.get();
       Assertions.assertEquals(savedUser.getId(), user.getId());
       Assertions.assertEquals(savedUser.getDob(), user.getDob());
       Assertions.assertFalse(user.getIsDeleted());
    }

}
