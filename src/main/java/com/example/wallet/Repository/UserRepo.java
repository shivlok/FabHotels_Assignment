package com.example.wallet.Repository;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.repository.CrudRepository;

import com.example.wallet.entities.User;

public interface UserRepo extends CrudRepository<User, Long> {

	 Boolean existsByUsername(String username);
	 
	 Optional<User> findByUsername(String username);
	 Optional<User> findById(@NotNull Long id);
}
