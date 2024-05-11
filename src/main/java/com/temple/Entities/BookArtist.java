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
public class BookArtist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty(message = "please enter a name")
	@Size(min = 5 , max = 25)
	private String artistName;
	@NotNull(message = "Plase Enter A Valid Date")
	private String date;
	
	@NotEmpty(message = "please enter a name")
	private String NoOFdays;
	
	@NotEmpty(message = "please enter a name")
	@Size(min = 5 , max = 25)
	private String yourName;
	@Email(message = "Please entere a valid email")
	private String yourEmail;
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
	private String yourPhone;
	
	
	public BookArtist() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookArtist(int id, @NotEmpty(message = "please enter a name") @Size(min = 5, max = 25) String artistName,
			@NotNull(message = "Plase Enter A Valid Date") String date,
			@NotEmpty(message = "please enter a name") String noOFdays,
			@NotEmpty(message = "please enter a name") @Size(min = 5, max = 25) String yourName,
			@Email(message = "Please entere a valid email") String yourEmail,
			@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits") String yourPhone) {
		super();
		this.id = id;
		this.artistName = artistName;
		this.date = date;
		NoOFdays = noOFdays;
		this.yourName = yourName;
		this.yourEmail = yourEmail;
		this.yourPhone = yourPhone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNoOFdays() {
		return NoOFdays;
	}
	public void setNoOFdays(String noOFdays) {
		NoOFdays = noOFdays;
	}
	public String getYourName() {
		return yourName;
	}
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	public String getYourEmail() {
		return yourEmail;
	}
	public void setYourEmail(String yourEmail) {
		this.yourEmail = yourEmail;
	}
	public String getYourPhone() {
		return yourPhone;
	}
	public void setYourPhone(String yourPhone) {
		this.yourPhone = yourPhone;
	}
	
	
	
}
