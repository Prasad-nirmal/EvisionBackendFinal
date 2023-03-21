package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "user")
@Entity
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "userId")
private int userId; 

@Column(name = "role", nullable = false)
private String role;

@Column(name = "emailId", nullable = false, unique = true)
private String emailId;

@Column(name = "password", nullable = false)
private String password;


public User() {
	super();
	// TODO Auto-generated constructor stub
}

public User(int userId,String role, String emailId, String password) {
	super();
	this.userId = userId;
	this.role = role;
	this.emailId = emailId;
	this.password = password;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "User [userId=" + userId + ", role=" + role + ", emailId=" + emailId + ", password=" + password + "]";
}




}