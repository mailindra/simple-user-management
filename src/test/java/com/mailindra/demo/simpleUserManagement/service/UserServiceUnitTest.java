package com.mailindra.demo.simpleUserManagement.service;

import com.mailindra.demo.simpleUserManagement.controller.dto.UserDto;
import com.mailindra.demo.simpleUserManagement.domain.User;
import com.mailindra.demo.simpleUserManagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

class UserServiceUnitTest {
    UserRepository userRepository;
    User testingUser;
    UserService userService;
    String defaultCreator ="SPRING_BOOT_TEST";
    String ssn="12345";

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        testingUser = User.builder()
                .id(123L)
                .name("Testing User")
                .ssn(ssn)
                .dob(LocalDate.parse("2022-03-12"))
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .createdBy(defaultCreator)
                .updatedBy(defaultCreator)
                .build();
        userService = new UserService(userRepository);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(testingUser);
        Mockito.when(userRepository.findByIdAndIsDeleted(Mockito.any(),Mockito.any())).thenReturn(Optional.of(testingUser));

    }

    @Test
    void createUser() {
        UserDto dto = new UserDto("Testing user", ssn, "2022-03-12");
        User createdUser = userService.createUser(dto);
        Assertions.assertEquals("Testing User",createdUser.getName());
        Assertions.assertEquals(ssn,createdUser.getSsn());
        Assertions.assertEquals(LocalDate.parse("2022-03-12"), createdUser.getDob());
        Assertions.assertEquals(defaultCreator, createdUser.getCreatedBy());
        Assertions.assertEquals(defaultCreator,createdUser.getUpdatedBy());
    }

    @Test
    void findUserById() {
        User foundUser = userService.findUserById(123L);
        Assertions.assertEquals(123L, foundUser.getId());

    }

    @Test
    void testConvertDOB_Success(){
        String dobString ="2022-01-12";
        LocalDate dob = userService.convertDOB(dobString);
        Assertions.assertEquals(2022, dob.getYear());
        Assertions.assertEquals(1, dob.getMonthValue());
        Assertions.assertEquals(12, dob.getDayOfMonth());
    }

    @Test
    void testFormatSsn_Success(){
        String ssn = userService.formatSocialSecurityNumber("13");
        Assertions.assertEquals("00013", ssn);
    }
}