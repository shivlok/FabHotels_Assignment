package com.example.wallet.Dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.wallet.entities.Transaction;
import com.example.wallet.entities.Wallet;

@Component
public class DtoMapper {
	public WalletDto toDto(Wallet wallet) {
		WalletDto walletDto= new WalletDto();
		walletDto.setCurrency(wallet.getCurrency());;
		walletDto.setId(wallet.getId());
		walletDto.setUserId(wallet.getUserId());
		walletDto.setWalletBalance(wallet.getWalletBalance());;
		return walletDto;
	}
	
public List<TransactionDto>converToDto(List<Transaction>transactions){
		List<TransactionDto> allTransactions = new ArrayList<>();
		transactions.forEach((transaction) -> {
			allTransactions.add(this.map(transaction));
			
		});
		
		return allTransactions;
	}

public TransactionDto map(Transaction transaction) {
	TransactionDto transactionDto = new TransactionDto();
	transactionDto.setAmount(transaction.getAmount());
	transactionDto.setCreatedAt(transaction.getTranactionTimestamp());
	transactionDto.setCurrency(transaction.getCurrency().toUpperCase());
	transactionDto.setId(transaction.getId());
	transactionDto.setSenderWalletId(String.valueOf(transaction.getWallet().getId()));
	transactionDto.setStatus(transaction.getStatus());
	transactionDto.setTransactionType(transaction.getTransactionType());
	transactionDto.setTransactionUniqueId(transaction.getTransactionUniqueId());
	return transactionDto;
}
	
	
}

