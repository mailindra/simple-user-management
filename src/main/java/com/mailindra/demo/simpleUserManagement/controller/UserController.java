package com.mailindra.demo.simpleUserManagement.controller;

import com.mailindra.demo.simpleUserManagement.controller.dto.UserDto;
import com.mailindra.demo.simpleUserManagement.controller.dto.UserUpdateDto;
import com.mailindra.demo.simpleUserManagement.domain.User;
import com.mailindra.demo.simpleUserManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto dto){
        User createdUser = userService.createUser(dto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserUpdateDto dto){
        User updatedUser = userService.updateUser(userId,dto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") Long userId){
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id")Long userId){
        return userService.deleteUser(userId);
    }
}
