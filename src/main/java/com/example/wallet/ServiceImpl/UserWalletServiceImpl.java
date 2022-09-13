package com.example.wallet.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.wallet.Dto.DtoMapper;
import com.example.wallet.Repository.UserWalletRepo;
import com.example.wallet.constants.CustomException;
import com.example.wallet.constants.Errors;
import com.example.wallet.constants.TransactionTypeValues;
import com.example.wallet.entities.Wallet;
import com.example.wallet.services.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService {
	
	@Autowired
	UserWalletRepo userWalletRepo;
	
	@Autowired
	DtoMapper dtoMapper;

	@Override
	public List<Wallet> findAllWallet() throws Exception {
		// TODO Auto-generated method stub
		try {
			return userWalletRepo.findAllByOrderByIdAsc();
		}
		catch(Exception e) {
			throw new Exception("Could not fetch wallets");
		}
	}

	@Override
	public Wallet createWallet(String userId,String currency) throws Exception {
		// TODO Auto-generated method stub
		try {
			Wallet newWallet = new Wallet(new BigDecimal(0.0), userId, currency);
			return userWalletRepo.save(newWallet);
		}
		catch(ObjectNotFoundException e) {
			throw new Exception("could not create wallet");
		}

	}

	@Override
	public Wallet getWalletById(Long id)  {
		// TODO Auto-generated method stub
		
			return userWalletRepo.findById(id).get();
		
	}

	@Override
	public ResponseEntity<?> addMoney(Long id, String amount) {
		// TODO Auto-generated method stub
		Wallet wallet =this.getWalletById(id);
		
		if(wallet != null && wallet.getId() == id) {
			BigDecimal newBalance = wallet.getWalletBalance().add(BigDecimal.valueOf(Double.valueOf(amount)));
			wallet.setWalletBalance(newBalance);
			userWalletRepo.save(wallet);
			return ResponseEntity.status(HttpStatus.OK.value()).body(dtoMapper.toDto(wallet));
		}
		return ResponseEntity.badRequest().body("amount could not be added to the wallet");
	}

	@Override
	public Wallet makeTransactionFromWallet(Wallet senderWallet, String transactionType, String amount) throws CustomException{
		// TODO Auto-generated method stub
		try {
			BigDecimal transactionAmount;
			Boolean isTransactionTypeCredit = (transactionType.toUpperCase().equals(TransactionTypeValues.CREDIT.toString()));
			if(isTransactionTypeCredit) {
				transactionAmount = new BigDecimal(amount).abs();
			}
			else {
				transactionAmount = new BigDecimal(amount).abs().negate();
			}
			
			Boolean isTransactionPossible =  (isTransactionTypeCredit || (senderWallet.getWalletBalance().compareTo(transactionAmount.abs()) >= 0));
			if(!isTransactionPossible) {
				throw new CustomException(String.format(Errors.INSUFFICIENT_BALANCE, senderWallet.getId(),transactionAmount,senderWallet.getWalletBalance()));
			}
			senderWallet.setWalletBalance(senderWallet.getWalletBalance().add(transactionAmount));
			
			return userWalletRepo.save(senderWallet);
		}
		catch(NumberFormatException ex) {
			throw new CustomException("Number format provides is not correct",HttpStatus.BAD_REQUEST.value());
		}
	}

}
