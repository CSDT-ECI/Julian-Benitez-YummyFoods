package com.userportal.spring.form;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="user")
public class User
{
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	private Login login;
	
	@OneToMany(mappedBy="user")
	private Set<Recipe> recipe;
	
	@Id
	@Column(name="userId",nullable=false)
	private String userId;
	
	@Column(name="userName", nullable=false)
	private String userName;
	
	@Column(name="emailId",nullable=false)
	private String userEmailId;
	
	@Transient
	private String userPassword;
	
	@Transient
	private String userConfirmPassword;
	
	@Transient
	private String userNewPassword;
	
	@Column(name="recipeRated",nullable=true)
	private String recipeRated;
	
	public String getRecipeRated() {
		return recipeRated;
	}
	public void setRecipeRated(String recipeRated) {
		this.recipeRated = recipeRated;
	}
	public String getUserNewPassword() {
		return userNewPassword;
	}
	public void setUserNewPassword(String userNewPassword) {
		this.userNewPassword = userNewPassword;
	}
	public Set<Recipe> getRecipe() {
		return recipe;
	}
	public void setRecipe(Set<Recipe> recipe) {
		this.recipe = recipe;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}
	public void setUserConfirmPassword(String userConfirmPassword) {
		this.userConfirmPassword = userConfirmPassword;
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
