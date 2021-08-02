package com.example.xmlprocessingshop.util;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
       return validator.validate(entity).isEmpty();
    }
}
