package com.example.wallet.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.wallet.constants.CustomException;
import com.example.wallet.entities.Wallet;


public interface UserWalletService {
	
	public List<Wallet> findAllWallet() throws Exception; 
	
	public Wallet createWallet(String userId, String currency) throws Exception;
	
	public Wallet getWalletById(Long id) throws Exception;
	
	public ResponseEntity<?> addMoney(Long id, String amount);
	
	public Wallet makeTransactionFromWallet(Wallet wallet,String transactionType,String amount) throws CustomException;

}
