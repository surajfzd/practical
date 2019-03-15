package com.capgemini.bean;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="CustomerWallet")
public class Customer {

	@Id
	@Column(name="Moble_Number")
	@NotNull(message="Mobile number cannot be null")
	@NotBlank(message="Mobile number cannot be blank")
	@Pattern(regexp = "[6-9][0-9]{9}")
	
	private String mobileNumber;
	
	@NotEmpty(message="Name cannot be empty")
	@Size(min=3,max=30,message="name size cannot be less than 3 or greater than 30")
	private String name;
	
	@Embedded
	@NotNull(message="Wallet cannot be null")
	private Wallet wallet;
	
	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [mobileNumber=" + mobileNumber + ", name=" + name + ", wallet=" + wallet + "]";
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNum) {
		this.mobileNumber = mobileNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		return true;
	}


}
