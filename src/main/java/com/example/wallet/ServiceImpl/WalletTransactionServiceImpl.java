package com.example.wallet.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.WalletTransactionRepo;
import com.example.wallet.constants.CustomException;
import com.example.wallet.constants.Errors;
import com.example.wallet.entities.Transaction;
import com.example.wallet.entities.Wallet;
import com.example.wallet.services.UserWalletService;
import com.example.wallet.services.WalletTransactionService;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {
	
	@Autowired
	UserWalletService userWalletService;
	
	@Autowired
	WalletTransactionService walletTransactionService;
	
	
	Errors errors;
	
	@Autowired
	WalletTransactionRepo walletTransactionRepo;

	@Override
	public List<Transaction> getTransactionsByWalletId(@NotNull Long walletId) throws Exception {
		// TODO Auto-generated method stub
		Wallet wallet =  userWalletService.getWalletById(walletId);
		
		if(wallet != null) {
			List<Transaction> allTransactions = walletTransactionRepo.findByWallet(wallet);
			return allTransactions;
		}
		return null;
	}

	@Override
	public Transaction createWalletTransaction(String transactionUniqueId, String currency, String senderWalletId,String transactionType, String amount) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		try {
			
			
			Wallet senderWallet = userWalletService.getWalletById(Long.valueOf(senderWalletId));
			
			if(senderWallet == null) {
				throw new CustomException(String.format(errors.WALLET_NOT_FOUND, senderWalletId),HttpStatus.BAD_REQUEST.value());
			}
			else {
				if( currency.toUpperCase().equals(senderWallet.getCurrency()) == false) {
					
					throw new CustomException(String.format(errors.CURRENCY_DO_NOT_MATCH, senderWalletId),HttpStatus.BAD_REQUEST.value());
				}
				senderWallet= userWalletService.makeTransactionFromWallet(senderWallet, transactionType, amount);
				
			}
			Transaction newTransaction = new Transaction(transactionUniqueId, senderWallet, new BigDecimal(amount), currency, transactionType.toUpperCase()); 
			if(senderWallet != null) {
				newTransaction.setStatus(true);
			}
			
			return walletTransactionRepo.save(newTransaction);
			
		}
		catch(NumberFormatException ex) {
			throw new CustomException(String.format("%s should be a number", amount),HttpStatus.BAD_REQUEST.value());
		}
		
	}

}
