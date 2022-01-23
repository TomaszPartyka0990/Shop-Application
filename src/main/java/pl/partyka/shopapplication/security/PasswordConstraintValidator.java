package pl.partyka.shopapplication.security;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(6, 30),
                new UppercaseCharacterRule(1),
                new SpecialCharacterRule(1),
                new WhitespaceRule()
        ));
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()){
            return true;
        }
        return false;
    }
}
