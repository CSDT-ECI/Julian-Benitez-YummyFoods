package com.yummyfoods.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.yummyfoods.spring.form.Recipe;


@Component
public class RecipeValidator implements Validator{

	@Override
	public boolean supports(Class<?> classz	) {
		// TODO Auto-generated method stub
		return Recipe.class.equals(classz);
	}

	@Override
	public void validate(Object arg0, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","recipeNameError", "Recipe name can't be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ingredients","recipeIngredientsError", "Ingredients can't be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "directions","recipeDirectionsError", "Directions can't be blank");
	}

}
