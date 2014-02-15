package com.userportal.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.userportal.spring.form.Login;

@Component
public class LoginValidator implements Validator
{

	@Override
	public boolean supports(Class<?> classz) 
	{
		return Login.class.equals(classz);
	}

	@Override
	public void validate(Object obj, Errors error)
	{
		System.out.println("Coming to vlaidator");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userId", "userIdError", "User Id can't be null or blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "userPassword", "userPasswordError", "Password can't be null or blank");
		
		
	}

}
