package com.example.wallet.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.wallet.Dto.DtoMapper;
import com.example.wallet.constants.AddMoneyRequest;
import com.example.wallet.entities.Wallet;
import com.example.wallet.services.UserWalletService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/wallet")
public class UserWalletController {
	
	@Autowired
	UserWalletService userWalletService;
	
	@Autowired
	DtoMapper dtoMapper;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserWalletController.class);	

	/***
	 * 
	 * @param wallet
	 * @return
	 * {
		    "id": 1,
		    "walletBalance": 0.00,
		    "userId": "1",
		    "currency": "INR"
       }
	 * @throws Exception
	 */
	@PostMapping("/create")
	@ResponseBody
	ResponseEntity<?> createWallet(@RequestBody Wallet wallet) throws Exception{
	
		logger.debug("UserWalletController.createWallet called ");
		String currency= wallet.getCurrency().toUpperCase();
		String userId = wallet.getUserId();
		return ResponseEntity.status(200).body(dtoMapper.toDto(userWalletService.createWallet(userId,currency)));
	}
	
	/***
	 * 
	 * @param id
	 * @return
	 * {
		    "id": 5,
		    "walletBalance": 50.00,
		    "userId": "1",
		    "currency": "INR"
       }
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	@ResponseBody
	ResponseEntity<?> getWalletdetails(@PathVariable("id") Long id) throws Exception{
		
		logger.debug("UserWalletController.getWalletdetails called with id {}",id);
			Wallet wallet = userWalletService.getWalletById(id);
			if(wallet != null && wallet.getId()==id) {
				return  ResponseEntity.status(HttpStatus.OK.value()).body(dtoMapper.toDto(wallet));
			}
			else {
				return ResponseEntity.badRequest().body("wallet doesnt exist!");
			}
		}
	
	/***
	 * 
	 * @param id
	 * @param 
	 * { "amount":"100"}
	 * @return
	 * {
		    "id": 5,
		    "walletBalance": 50.00,
		    "userId": "1",
		    "currency": "INR"
       }
	 */
	@PutMapping("/{id}/addMoney")
	@ResponseBody
	ResponseEntity<?>addMoneyToWallet(@PathVariable("id") Long id,@RequestBody AddMoneyRequest addMoneyRequest){
		logger.debug("UserWalletController.addMoneyToWallet called with id {} and amaount",id,addMoneyRequest.getAmount());
		String amount = addMoneyRequest.getAmount();
		return ResponseEntity.ok(userWalletService.addMoney(id,amount)).getBody();
	}
		
	}
	

