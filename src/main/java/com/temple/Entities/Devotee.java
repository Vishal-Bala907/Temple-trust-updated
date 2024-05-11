package com.temple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Devotee {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	@NotEmpty(message = "Username Cannot be Empty")
	private String username;
	@Email
	private String email;
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
	private String phone;
	@NotEmpty(message = "Password Cannot be Empty")
	private String password;

	private String role;
	private boolean isEnabled;

	public Devotee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Devotee(long id, @NotEmpty(message = "Username Cannot be Empty") String username, @Email String email,
			@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits") String phone,
			@NotEmpty(message = "Password Cannot be Empty") String password, String role, boolean isEnabled) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "Devotee [id=" + id + ", username=" + username + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", role=" + role + ", isEnabled=" + isEnabled + "]";
	}
	

}
