package com.example.jsonprocessing.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {
    <E>boolean isValid(E entity);
}
