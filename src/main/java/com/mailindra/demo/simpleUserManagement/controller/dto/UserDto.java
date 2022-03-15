package com.mailindra.demo.simpleUserManagement.controller.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Digits(integer = 5, fraction = 0)
    private int ssn;

    @NotNull
    private Date dob;


}
