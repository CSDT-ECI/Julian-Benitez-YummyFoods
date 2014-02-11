package com.userportal.spring.form;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="recipe")
public class Recipe 
{
	@Id
	@Column(name="recipeId")
	@GeneratedValue
	private Integer recipeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="forPeople")
	private Integer forPeople;
	
	@Column(name="ingredients")
	private String ingredients;
	
	@Column(name="directions")
	private String directions;
	
	@Column(name="fileName")
    private String fileName;
 
	
	@Column(name="pic")
	@Lob
	private Blob pic;
	
	@Column(name="contentType")
    private String contentType;
    
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getForPeople() {
		return forPeople;
	}

	public void setForPeople(Integer forPeople) {
		this.forPeople = forPeople;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Blob getPic() {
		return pic;
	}

	public void setPic(Blob pic) {
		this.pic = pic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
