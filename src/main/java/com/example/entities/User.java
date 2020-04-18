package com.example.entities;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message="FirstName is required")
	private String firstName;
	
	@NotEmpty(message="LastName is required")
	private String lastName;
	
	@NotEmpty(message="Email is required")
	@Email(message="Please enter a valid email")
	@Column(name="email",unique=true)
	private String email;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, message="Password shoud not be less than  8 caracters")
	private String password;
	
	
	private String photo;
	
	
	private boolean isEnabled;
	

	
	public User() {
		
	}
	

	
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}






	public User(int id, String firstName, String lastName, String email, String password, String photo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.photo = photo;
	}






	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}




	public boolean isEnabled() {
		return isEnabled;
	}




	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}




	public void setUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
