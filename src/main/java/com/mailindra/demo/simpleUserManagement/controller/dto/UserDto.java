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

    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    @Size(max = 5)
    private String ssn;

    @NotNull
    @ValidDOBConstraint()
    private String dob;


}
