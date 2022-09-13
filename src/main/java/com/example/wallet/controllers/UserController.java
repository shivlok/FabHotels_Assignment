package com.example.wallet.controllers;

import java.nio.file.attribute.UserPrincipalNotFoundException;


import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet.Repository.UserRepo;
import com.example.wallet.ServiceImpl.UserDetailsServiceImplement;
import com.example.wallet.constants.LoginRequest;
import com.example.wallet.constants.LoginResponse;
import com.example.wallet.constants.RegisterRequest;
import com.example.wallet.entities.User;
import com.example.wallet.services.JwtHelperService;
import com.example.wallet.services.UserServices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	AuthenticationManager auth;
	
	@Autowired
	UserDetailsServiceImplement userDetailsServiceImplement;
	
	@Autowired
	JwtHelperService jwtHelperService;
	
	/***
	 * 
	 * @param 
	 * {
		    "username":"shivlok",
		    "password":"shivlok@123",
		    "isActive":true,
		    "email":"shivlok@gmail.com"
       }
	 * @return REGISTRATION SUCCESSFULL!
	 * 
	 */
	@PostMapping("/register")
	@ResponseBody
	ResponseEntity<?>registerUser(@RequestBody RegisterRequest registerRequest){
		
		if(userRepo.existsByUsername(registerRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new String("Username already exists! Please enter another username."));
			
		}
		User user = new User(registerRequest.getUsername(),encoder.encode(registerRequest.getPassword())
				              ,registerRequest.getEmail(),registerRequest.getIsActive());
		
		userRepo.save(user);
		logger.info(user.getUsername());
		return ResponseEntity.ok(new String("REGISTRATION SUCCESSFULL!"));
		
   }
	
	/***
	 * 
	 * @param loginRequest
	 * @return
	 * {
	 *   "jwt":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGl2bG9rIiwiZXhwIjoxNjYzMDY0NzU0LCJqdGkiOiI5MGVmYjBiZC1hYzIyLTQzYmItOWFhMy02NTI2ZDY0ZWMzYzIifQ.sZGXYOyKnpnMki1BZ4b-iLP8h9oqoy31ONLOweVH9-E"
	 * }
	 * @throws Exception
	 */
	
	@PostMapping("/login")
	@ResponseBody
	ResponseEntity<?>loginUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
		try {
			Authentication authentication =auth.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		
		UserDetails user = userDetailsServiceImplement.loadUserByUsername(loginRequest.getUsername());
		
		String jwtToken = jwtHelperService.createJwtTokenByUser(user);
		
		return ResponseEntity.ok(new LoginResponse(jwtToken));
		
	}
	/***
	 * 
	 * @param id
	 * @return
	 *  {
		    "username":"shivlok",
		    "password":"shivlok@123",
		    "isActive":true,
		    "email":"shivlok@gmail.com"
       }
	 * @throws UserPrincipalNotFoundException
	 */
	@GetMapping("/view/{id}")
	@ResponseBody
	ResponseEntity<?>getUserFromUserId(@PathVariable Long id) throws UserPrincipalNotFoundException{
        logger.debug("Called UserConroller.getUserFromUserId with parameter id={}",id );

		return ResponseEntity.ok(userServices.getUserById(id));
	}
	
	/***
	 * 
	 * @param id
	 * @param user
	 * @throws AccountNotFoundException
	 */
	@PutMapping("/update/{id}")
	@ResponseBody
	void updateUserDetails(@PathVariable Long id, @RequestBody User user) throws AccountNotFoundException {
		User existingUser = userServices.getUserById(id);
		if(existingUser != null && existingUser.getUsername()==user.getUsername()) {
			userServices.updateUser(id, user);
		}
	}
	
}
