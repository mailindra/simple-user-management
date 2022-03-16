package com.mailindra.demo.simpleUserManagement.service;

import com.mailindra.demo.simpleUserManagement.controller.dto.UserDto;
import com.mailindra.demo.simpleUserManagement.controller.dto.UserUpdateDto;
import com.mailindra.demo.simpleUserManagement.domain.User;
import com.mailindra.demo.simpleUserManagement.exception.ConflictBusinessException;
import com.mailindra.demo.simpleUserManagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(UserDto dto){
        String socialSecurityNumber = formatSocialSecurityNumber( dto.getSsn());
        Optional<User> existingUserWithSSNActive = userRepository.findBySsnAndIsDeleted(socialSecurityNumber, Boolean.FALSE);
        if(existingUserWithSSNActive.isPresent())
            throw new ConflictBusinessException("CONFLICT",30002,
                    "Record with SSN "+ socialSecurityNumber +" already exists in the system");

        User user = createUserEntityValue(dto);
        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserUpdateDto dto)  {
        User user = findUserById(userId);
        user.setName(dto.getName());
        user.setDob(convertDOB(dto.getDob()));
        return userRepository.saveAndFlush(user);
    }

    public boolean deleteUser(Long userId){
        User user = findUserById(userId);
        user.setIsDeleted(Boolean.TRUE);
        userRepository.saveAndFlush(user);
        return true;

    }

    public User findUserById(Long id){
        return userRepository.findByIdAndIsDeleted(id,Boolean.FALSE).orElseThrow(()->new EntityNotFoundException("No such resource with id: "+id));

    }

    protected User createUserEntityValue(UserDto dto){
        String defaultCreator ="SPRING_BOOT_TEST";
        String socialSecurityNumber = formatSocialSecurityNumber( dto.getSsn());
        LocalDate dob = convertDOB(dto.getDob());
        User user = new User();
        user.setName(dto.getName());
        user.setSsn(socialSecurityNumber);
        user.setDob(dob);
        user.setCreatedBy(defaultCreator);
        user.setUpdatedBy(defaultCreator);
        return user;
    }

    protected String formatSocialSecurityNumber(int ssnInInteger){
        return String.format("%05d", ssnInInteger);
    }

    protected LocalDate convertDOB(String dobString){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        LocalDate dob = LocalDate.parse(dobString,dateFormatter);
        return dob;
    }
}
