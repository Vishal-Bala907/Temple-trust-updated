package com.temple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Products {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long p_id;
		@NotEmpty(message = "Name Cannot Be Empty")
		private String name;
		@NotEmpty(message = "Please Enter Some Description")
	    private String description;
//		@NotEmpty(message = "Price cannot be empty")
	    private double price;
	    private String  imageUrl;
	    
		public Products() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Products(long p_id, @NotEmpty(message = "Name Cannot Be Empty") String name,
				@NotEmpty(message = "Please Enter Some Description") String description,
				@NotEmpty(message = "Price cannot be empty") double price, String imageUrl) {
			super();
			this.p_id = p_id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.imageUrl = imageUrl;
		}
		public long getP_id() {
			return p_id;
		}
		public void setP_id(long p_id) {
			this.p_id = p_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		@Override
		public String toString() {
			return "Products [p_id=" + p_id + ", name=" + name + ", description=" + description + ", price=" + price
					+ ", imageUrl=" + imageUrl + "]";
		}
		
	    
	    
}
