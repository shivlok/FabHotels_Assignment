package com.example.wallet.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String transactionUniqueId;
	
	@NotNull(message = "Sender wallet should be present")
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
//	@NotNull(message = "Reciever wallet should be present")
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "wallet_id")
//	private Wallet recieverWallet;
//	
	private BigDecimal amount;
	
	private String currency;
	
	private String transactionType;
	
    @Temporal(TemporalType.TIMESTAMP)
    private Date tranactionTimestamp;

    @Column( columnDefinition ="boolean default false")
	private Boolean status;
	
	

    public Transaction() {}
    
	public Transaction(String transactionUniqueId, Wallet senderWallet, BigDecimal amount,String currency,String transactionType) {
		this.transactionUniqueId = transactionUniqueId;
		this.wallet = senderWallet;
//		this.recieverWallet = recieverWallet;
		this.amount = amount;
		this.currency = currency;
		this.transactionType=transactionType;
		this.tranactionTimestamp= new Date();
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionUniqueId() {
		return transactionUniqueId;
	}

	public void setTransactionUniqueId(String transactionUniqueId) {
		this.transactionUniqueId = transactionUniqueId;
	}

	public Wallet getSenderWallet() {
		return wallet;
	}

	public void setSenderWallet(Wallet senderWallet) {
		this.wallet = senderWallet;
	}

//	public Wallet getRecieverWallet() {
//		return recieverWallet;
//	}
//
//	public void setRecieverWallet(Wallet recieverWallet) {
//		this.recieverWallet = recieverWallet;
//	}

	public BigDecimal getAmount() {
		return amount;
	}

	
	public Long getId() {
		return id;
	}
	
	

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
	
	 public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTranactionTimestamp() {
			return tranactionTimestamp;
		}

		public void setTranactionTimestamp(Date tranactionTimestamp) {
			this.tranactionTimestamp = tranactionTimestamp;
		}

		public Boolean getStatus() {
			return status;
		}

		public void setStatus(Boolean status) {
			this.status = status;
		}
	

}
