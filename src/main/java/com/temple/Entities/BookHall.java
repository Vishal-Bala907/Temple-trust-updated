package com.temple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class BookHall {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Event Name is required")
	private String eventName;

	@NotNull(message = "Event Date is required")
	private String date;

	@NotNull(message = "Number of Days is required")
	@Min(value = 1, message = "Number of Days must be at least 1")
	private Integer NoOFdays;

	@NotBlank(message = "Your Name is required")
	private String yourName;

	@Email(message = "Invalid email address")
	@NotBlank(message = "Your Email is required")
	private String yourEmail;

	@Pattern(regexp = "\\d{10}", message = "Invalid phone number")
	@NotBlank(message = "Your Phone is required")
	private String yourPhone;

	// Getters and setters
	// You can generate them using your IDE or write them manually]
	

	public String getEventName() {
		return eventName;
	}

	public BookHall(@NotBlank(message = "Event Name is required") String eventName,
			@NotNull(message = "Event Date is required") String date,
			@NotNull(message = "Number of Days is required") @Min(value = 1, message = "Number of Days must be at least 1") Integer noOFdays,
			@NotBlank(message = "Your Name is required") String yourName,
			@Email(message = "Invalid email address") @NotBlank(message = "Your Email is required") String yourEmail,
			@Pattern(regexp = "\\d{10}", message = "Invalid phone number") @NotBlank(message = "Your Phone is required") String yourPhone) {
		super();
		this.eventName = eventName;
		this.date = date;
		NoOFdays = noOFdays;
		this.yourName = yourName;
		this.yourEmail = yourEmail;
		this.yourPhone = yourPhone;
	}

	public BookHall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNoOFdays() {
		return NoOFdays;
	}

	public void setNoOFdays(Integer NoOFdays) {
		this.NoOFdays = NoOFdays;
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
