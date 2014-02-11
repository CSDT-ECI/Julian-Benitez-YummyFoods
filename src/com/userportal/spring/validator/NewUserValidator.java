package com.userportal.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.userportal.spring.controller.NewUserRegistration;

import com.userportal.spring.form.User;

@Component
public class NewUserValidator  implements Validator
{
	@Override
	public boolean supports(Class<?> classz) 
	{
		return User.class.equals(classz);
	}

	@Override
	public void validate(Object obj, Errors error) 
	{
			ValidationUtils.rejectIfEmptyOrWhitespace(error, "userId", "userIdEmpty", "User ID can't be empty");
		ValidationUtils.rejectIfEmpty(error, "userEmailId", "userEmailIdEmpty", "Email ID cann't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userPassword", "userPasswordEmpty", "Password cant be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userConfirmPassword", "userConfirmPasswordEmpty", "Confirm Password cant be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userName", "userNameEmpty","Name can't be empty");
	}

}
