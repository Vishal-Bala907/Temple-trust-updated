package com.temple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class PoojaSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty(message = "please enter a name")
	@Size(min = 5 , max = 25)
	private String name;
	@Email(message = "Please entere a valid email")
	private String email;
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
	private String number;
	@NotNull(message = "Plase Enter A Valid Date")
	private String date;
	@NotNull(message = "Plase Enter A Valid Time")
	private String time;

	public PoojaSlot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PoojaSlot(long id, @NotEmpty(message = "please enter a name") String name,
			@Email(message = "Please entere a valid email") String email,
			@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits") String number,
			@NotNull(message = "Plase Enter A Valid Date") String date,
			@NotNull(message = "Plase Enter A Valid Time") String time) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.date = date;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
