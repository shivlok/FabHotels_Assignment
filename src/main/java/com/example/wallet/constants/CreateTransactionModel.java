package com.example.wallet.constants;

public class CreateTransactionModel {

	
    private String transactionUniqueId;

    private String currency;

    private String senderWalletId;
 
    private String transactionType;

    private String amount;
    
    public CreateTransactionModel() {}
    
    

	public CreateTransactionModel(String transactionUniqueId, String currency, String senderWalletId,
			String transactionType, String amount) {
		this.transactionUniqueId = transactionUniqueId;
		this.currency = currency;
		this.senderWalletId = senderWalletId;
		this.transactionType = transactionType;
		this.amount = amount;
	}



	public String getTransactionUniqueId() {
		return transactionUniqueId;
	}

	public void setTransactionUniqueId(String transactionUniqueId) {
		this.transactionUniqueId = transactionUniqueId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSenderWalletId() {
		return senderWalletId;
	}

	public void setSenderWalletId(String senderWalletId) {
		this.senderWalletId = senderWalletId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
    
    

}
