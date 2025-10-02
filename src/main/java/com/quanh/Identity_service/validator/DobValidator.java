package com.quanh.Identity_service.validator;

import jakarta.validation.ConstraintValidator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {
    private int min;

    @Override
    public void initialize(DobConstraint constraintAnnotation) {    //Lấy giá trị đã khai báo trong annotation về để so sánh trong isValid
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate dob, jakarta.validation.ConstraintValidatorContext context) {
        if (Objects.isNull(dob)) {
            return true;   //Best practice: null is valid, use @NotNull to check null
        }
        else {
            return ChronoUnit.YEARS.between(dob, LocalDate.now()) >= this.min;
        }
    }
}
