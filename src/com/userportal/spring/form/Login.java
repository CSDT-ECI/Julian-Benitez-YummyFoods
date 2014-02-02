package com.userportal.spring.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="login", schema="userportalschema")
public class Login 
{
	
	@Id
	@Column(name="userId")
	private String userId;
	
	@Column(name="userPassword")
	private String userPassword;
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

}
