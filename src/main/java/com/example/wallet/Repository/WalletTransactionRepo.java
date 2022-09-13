package com.example.wallet.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.wallet.entities.Transaction;
import com.example.wallet.entities.Wallet;

public interface WalletTransactionRepo extends CrudRepository<Transaction, Long>{
	
	List<Transaction> findByWallet(Wallet wallet);

}
