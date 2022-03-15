package com.mailindra.demo.simpleUserManagement.controller.dto;

import com.mailindra.demo.simpleUserManagement.controller.validator.ValidDOBConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserUpdateDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @ValidDOBConstraint()
    private String dob;


}
