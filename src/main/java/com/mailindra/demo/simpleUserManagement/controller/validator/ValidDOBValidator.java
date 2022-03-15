package com.mailindra.demo.simpleUserManagement.controller.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ValidDOBValidator implements ConstraintValidator<ValidDOBConstraint, String> {
    private Boolean isOptional;

    @Override
    public void initialize(ValidDOBConstraint validDate) {
        this.isOptional = validDate.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean validDate = isValidFormat("yyyy-MM-dd", value);

        return isOptional ? (validDate ||(Strings.isBlank(value))) : validDate;
    }

    private static boolean isValidFormat(String format, String value) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(value, dateFormatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
