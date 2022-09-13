package com.example.wallet.services;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.wallet.entities.User;


public interface UserServices {

public void updateUser(@NotNull Long id,User user) throws UsernameNotFoundException ;
	
	public User getUserById(@NotNull Long id) throws  UsernameNotFoundException;
}
