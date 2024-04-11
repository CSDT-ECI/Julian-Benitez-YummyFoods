package com.yummyfoods.spring.validator;

import com.yummyfoods.spring.form.User;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserChangePasswordValidatorTest {

    @Test
    public void testChangePasswordWithValidFields() {
        UserChangePasswordValidator userChangePasswordValidator = new UserChangePasswordValidator();
        User validUser = new User();
        validUser.setUserPassword("password");
        validUser.setUserConfirmPassword("password");
        validUser.setUserNewPassword("newPassword");
        Errors errors = new BeanPropertyBindingResult(validUser, "validUser");
        userChangePasswordValidator.validate(validUser, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testChangePasswordWithMissingNewPasswordFieldShouldError() {
        UserChangePasswordValidator userChangePasswordValidator = new UserChangePasswordValidator();
        User validUser = new User();
        validUser.setUserPassword("password");
        validUser.setUserConfirmPassword("password");
        Errors errors = new BeanPropertyBindingResult(validUser, "invalidUser");
        userChangePasswordValidator.validate(validUser, errors);
        assertTrue(errors.hasErrors());
    }
}
