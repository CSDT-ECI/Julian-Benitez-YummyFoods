package com.userportal.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.userportal.spring.form.User;

@Component
public class ProfileUpdateValidator implements Validator
{

	@Override
	public boolean supports(Class<?> arg0) 
	{
		return User.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
