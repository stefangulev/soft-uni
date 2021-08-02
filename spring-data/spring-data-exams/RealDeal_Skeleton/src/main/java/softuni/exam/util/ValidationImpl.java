package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationImpl implements ValidationUtil {

    public final Validator validator;

    public ValidationImpl() {

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }
}
