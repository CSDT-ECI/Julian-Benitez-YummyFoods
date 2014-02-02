package com.userportal.spring.form;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User
{
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	private Login login;
	
	@Id
	@Column(name="userId",nullable=false)
	private String userId;
	
	@Column(name="userName", nullable=false)
	private String userName;
	
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
