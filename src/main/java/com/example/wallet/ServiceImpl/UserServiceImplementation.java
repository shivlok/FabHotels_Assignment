package com.example.wallet.ServiceImpl;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.wallet.Repository.UserRepo;
import com.example.wallet.entities.User;
import com.example.wallet.services.UserServices;

@Service
public class UserServiceImplementation implements UserServices{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public void updateUser(@NotNull Long id, User updatedUser) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		if(userRepo.findById(id).isPresent()) {
			User existingUser =userRepo.findById(id).get();
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setIsActive(updatedUser.getIsActive());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setUsername(updatedUser.getUsername());
			userRepo.save(existingUser);
		}
		else
			throw new UsernameNotFoundException("User does not exist");
	}

	@Override
	public User getUserById(@NotNull Long id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<User> optionalUser=userRepo.findById(id);
			User user = optionalUser.get();
			return user;
		}
		catch(UsernameNotFoundException e){
			throw new UsernameNotFoundException("Invalid user id");
		}
	}

	
}
