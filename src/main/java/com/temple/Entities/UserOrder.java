package com.temple.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oid;
	private String customerName;
	private String customerAddress;
	private String pincode;
	private String number;

	private String itemId;
	private String itemName;
	private String itemPrice;

	public UserOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserOrder(int oid, String customerName, String customerAddress, String pincode, String number, String itemId,
			String itemName, String itemPrice) {
		super();
		this.oid = oid;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.pincode = pincode;
		this.number = number;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", pincode=" + pincode + ", number=" + number + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", itemPrice=" + itemPrice + ", quantity=" + "]";
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

}
