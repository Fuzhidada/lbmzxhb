package com.example.config;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoomVk implements ConstraintValidator<Moom, Integer> {

    @Override
    public void initialize(Moom constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        /**
         *  随便叫了个眼
         */
        return 200 >= s;
    }
}
