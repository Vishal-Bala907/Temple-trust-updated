package com.temple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class DonationForm {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
	 	@NotNull(message = "Donation type is required")
	    private String donationType;
	    
	    @NotBlank(message = "Amount or items cannot be empty")
	    private String amountOrItems;
	    
	    @NotBlank(message = "Your name cannot be empty")
	    private String contactName;
	    
	    @Email(message = "Invalid email address")
	    @NotBlank(message = "Your email cannot be empty")
	    private String contactEmail;
	    
	    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
	    @NotBlank(message = "Your phone number cannot be empty")
	    private String contactPhone;
	    
	    @NotBlank(message = "Your address cannot be empty")
	    private String contactAddress;
	    
	    // Getters and setters
	    // You can generate them using your IDE or write them manually
	    
	    public DonationForm() {
	    	super();
	    	// TODO Auto-generated constructor stub
	    }
	    
	    
	    public DonationForm(@NotNull(message = "Donation type is required") String donationType,
				@NotBlank(message = "Amount or items cannot be empty") String amountOrItems,
				@NotBlank(message = "Your name cannot be empty") String contactName,
				@Email(message = "Invalid email address") @NotBlank(message = "Your email cannot be empty") String contactEmail,
				@Pattern(regexp = "\\d{10}", message = "Invalid phone number") @NotBlank(message = "Your phone number cannot be empty") String contactPhone,
				@NotBlank(message = "Your address cannot be empty") String contactAddress) {
			super();
			this.donationType = donationType;
			this.amountOrItems = amountOrItems;
			this.contactName = contactName;
			this.contactEmail = contactEmail;
			this.contactPhone = contactPhone;
			this.contactAddress = contactAddress;
		}


		public String getDonationType() {
	        return donationType;
	    }

		public void setDonationType(String donationType) {
	        this.donationType = donationType;
	    }

	    public String getAmountOrItems() {
	        return amountOrItems;
	    }

	    public void setAmountOrItems(String amountOrItems) {
	        this.amountOrItems = amountOrItems;
	    }

	    public String getContactName() {
	        return contactName;
	    }

	    public void setContactName(String contactName) {
	        this.contactName = contactName;
	    }

	    public String getContactEmail() {
	        return contactEmail;
	    }

	    public void setContactEmail(String contactEmail) {
	        this.contactEmail = contactEmail;
	    }

	    public String getContactPhone() {
	        return contactPhone;
	    }

	    public void setContactPhone(String contactPhone) {
	        this.contactPhone = contactPhone;
	    }

	    public String getContactAddress() {
	        return contactAddress;
	    }

	    public void setContactAddress(String contactAddress) {
	        this.contactAddress = contactAddress;
	    }
}
