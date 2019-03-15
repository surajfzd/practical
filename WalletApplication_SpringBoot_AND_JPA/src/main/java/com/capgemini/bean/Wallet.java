package com.capgemini.bean;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Embeddable
public class Wallet {

	@Column(name="Balance")
	@NotNull(message="balance cannot be null")
	@NotBlank(message="balance cannot be blank")
	@PositiveOrZero(message="balance cannot be less than zero")
	private BigDecimal balance;

	
	@Override
	public String toString() {
		return "Wallet [balance=" + balance + "]";
	}

	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}