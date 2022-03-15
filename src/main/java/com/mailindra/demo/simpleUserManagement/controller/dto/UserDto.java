package com.mailindra.demo.simpleUserManagement.controller.dto;

import com.mailindra.demo.simpleUserManagement.controller.validator.ValidDOBConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;


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
    @ValidDOBConstraint()
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private String dob;


}
