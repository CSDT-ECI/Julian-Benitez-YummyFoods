package com.yummyfoods.spring.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.yummyfoods.spring.form.User;

@Component
public class UserChangePasswordValidator implements Validator 
{
	@Override
	public boolean supports(Class<?> classz) 
	{
		
		return User.class.equals(classz);
	}

	@Override
	public void validate(Object obj, Errors error) 
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userPassword", "userPasswordEmpty", "Password can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userConfirmPassword", "userConfirmPasswordEmpty", "Confirm Password can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userNewPassword", "userNewPasswordEmpty", "New Password can't be empty");
	}
}
