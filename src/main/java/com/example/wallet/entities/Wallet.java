package com.example.wallet.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal walletBalance;
	
	@NotNull
	private String userId;
	
	@Column(columnDefinition ="varchar(255) default 'INR'")
	private String currency;
	
	@OneToMany(targetEntity = Transaction.class,fetch= FetchType.LAZY)
	private List<Transaction>allTransaction;
	
	public Long getId() {
		return id;
	}
	
	public Wallet() {
		
	}

	public Wallet(BigDecimal walletBalance, @NotNull String userId,String currency) {
		this.walletBalance = walletBalance;
		this.userId = userId;
		this.currency=currency;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(BigDecimal walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Transaction> getAllTransaction() {
		return allTransaction;
	}

	public void setAllTransaction(List<Transaction> allTransaction) {
		this.allTransaction = allTransaction;
	}

	
	
	
	
}
