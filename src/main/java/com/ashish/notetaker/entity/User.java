package com.ashish.notetaker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false,length=300)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private boolean enabled;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", email=" + email + ", password=" + password + ", name=" + name + ", role=" + role
				+ ", enabled=" + enabled + "]";
	}

	public User(int uid, String email, String password, String name, String role, boolean enabled) {
		super();
		this.uid = uid;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.enabled = enabled;
	}

	public User() {
		super();
	}

	
	
	
	
	
	
	

}
