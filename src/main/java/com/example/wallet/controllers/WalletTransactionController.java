package com.example.wallet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet.Dto.DtoMapper;
import com.example.wallet.constants.CreateTransactionModel;
import com.example.wallet.constants.CustomException;
import com.example.wallet.entities.Transaction;
import com.example.wallet.entities.Wallet;
import com.example.wallet.services.UserWalletService;
import com.example.wallet.services.WalletTransactionService;

@RestController
@RequestMapping("/transaction")
public class WalletTransactionController {
	
	private static final Logger log = LoggerFactory.getLogger(WalletTransactionController.class);

	@Autowired
	WalletTransactionService walletTransactionService;
	
	@Autowired
	DtoMapper dtoMapper;
	
	/***
	 * 
	 * @param walletid
	 * @return
	 * [
		    {
		        "id": 8,
		        "transactionUniqueId": "4",
		        "senderWalletId": "5",
		        "status": true,
		        "transactionType": "DEBIT",
		        "currency": "INR",
		        "amount": 50.00,
		        "createdAt": "2022-09-13T04:07:21.000+00:00"
		    },
		    {
		        "id": 10,
		        "transactionUniqueId": "7",
		        "senderWalletId": "5",
		        "status": true,
		        "transactionType": "DEBIT",
		        "currency": "INR",
		        "amount": 10.00,
		        "createdAt": "2022-09-13T04:20:40.000+00:00"
		    },
		    {
		        "id": 11,
		        "transactionUniqueId": "8",
		        "senderWalletId": "5",
		        "status": true,
		        "transactionType": "CREDIT",
		        "currency": "INR",
		        "amount": 10.00,
		        "createdAt": "2022-09-13T04:21:36.000+00:00"
		    }
		 ]
	 * @throws Exception
	 */
	
	@GetMapping("/wallet/{id}")
	@ResponseBody
	ResponseEntity<?> getAllTransactionsById(@PathVariable("id") Long id) throws Exception{
		
		log.debug("WalletTransactionController.getAllTransactionsByWalletId called with walletId {}",id);
		
		List<Transaction> transactions = walletTransactionService.getTransactionsByWalletId(id);
		if(transactions != null) {
			return ResponseEntity.status(HttpStatus.OK.value()).body(dtoMapper.converToDto(transactions));
		}
		return ResponseEntity.ok().body(new ArrayList<Transaction>());
		
	}
	
	/***
	 * 
	 * @param 
	 * {
		    "transactionUniqueId":"11",
		    "currency":"inr",
		    "transactionType":"credit",
		    "senderWalletId":"5",
		    "amount":"10"
       }
	 * 
	 * 
	 * 
	 * @return
	 * {
		    "id": 14,
		    "transactionUniqueId": "11",
		    "senderWalletId": "5",
		    "status": true,
		    "transactionType": "CREDIT",
		    "currency": "inr",
		    "amount": 10,
		    "createdAt": "2022-09-13T05:25:03.123+00:00"
       }
	 * @throws NumberFormatException
	 * @throws CustomException
	 * @throws Exception
	 */
	@PostMapping("/create/{walletId}")
	@ResponseBody
	ResponseEntity<?> createTransaction(@RequestBody CreateTransactionModel transactionModel) throws NumberFormatException, CustomException, Exception{
		log.debug("WalletTransactionController.createTransaction called with transaction model {}",transactionModel.getClass());
		
		Transaction newTransaction = walletTransactionService
											.createWalletTransaction(transactionModel.getTransactionUniqueId()
																	,transactionModel.getCurrency().toUpperCase()
																	,transactionModel.getSenderWalletId()
																	,transactionModel.getTransactionType().toUpperCase()
																	,transactionModel.getAmount());
						
		log.info("Transaction created with id=" + newTransaction.getId());
		
		return ResponseEntity.status(HttpStatus.OK.value()).body(dtoMapper.map(newTransaction));
	}
	

}
