package com.example.wallet.services;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.example.wallet.constants.CustomException;
import com.example.wallet.entities.Transaction;


public interface WalletTransactionService {

	 public List<Transaction> getTransactionsByWalletId(@NotNull Long walletId) throws Exception;
	 public Transaction createWalletTransaction(String transactionUniqueId,String currency,String senderWalletId,String type,String amount) throws CustomException, NumberFormatException, Exception;
}
