package com.example.wallet.Dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {
	
	private Long id;
	
	private String transactionUniqueId;
	
	private String senderWalletId;
	
	private Boolean status;
	
	private String transactionType;
	private String currency;
	private BigDecimal amount;
    private Date createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransactionUniqueId() {
		return transactionUniqueId;
	}
	public void setTransactionUniqueId(String transactionUniqueId) {
		this.transactionUniqueId = transactionUniqueId;
	}
	public String getSenderWalletId() {
		return senderWalletId;
	}
	public void setSenderWalletId(String senderWalletId) {
		this.senderWalletId = senderWalletId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
    
    
	
}
