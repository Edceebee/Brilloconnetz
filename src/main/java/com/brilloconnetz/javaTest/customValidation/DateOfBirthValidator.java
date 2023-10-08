package com.brilloconnetz.javaTest.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthValidation, String> {

    private static final int MIN_AGE = 16;

    @Override
    public void initialize(DateOfBirthValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            return false;  // Date of birth cannot be empty
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date dob = sdf.parse(dateOfBirth);
            Date currentDate = new Date();
            long ageInMillis = currentDate.getTime() - dob.getTime();
            long years = ageInMillis / (1000L * 60 * 60 * 24 * 365);

            return years >= MIN_AGE;
        } catch (ParseException e) {
            return false;  // Invalid date format
        }
    }
}