package com.example.wallet.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.wallet.entities.Wallet;

public interface UserWalletRepo extends CrudRepository<Wallet, Long>{
	
	Optional<Wallet>findById(Long id);

	List<Wallet> findAllByOrderByIdAsc();
}
