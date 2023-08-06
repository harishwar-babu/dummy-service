package com.college.customization;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
public class ValidationAge implements ConstraintValidator<AgeValidator, LocalDate> {

    @Override
    public void initialize(AgeValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        int years = Period.between(value,LocalDate.now()).getYears();
        return years>18;
    }
}
