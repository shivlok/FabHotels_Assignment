package com.example.wallet.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelperService {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtHelperService.class);
	
	@Value("${jwtSecretKey}")
	private String jwtSecretKey;
	
	@Value("${jwtExpirationTime}")
	private int jwtExpirationTime;
	
	public String createJwtTokenByUser(UserDetails user) {
		Instant now = Instant.now();
		String username=user.getUsername();
		String jwtToken=Jwts.builder()
		   .setSubject(username).setExpiration(new Date((new Date()).getTime() + jwtExpirationTime))
		    .setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256,jwtSecretKey).compact();
		
		return jwtToken;
	}
	
	public Boolean validateJwtToken(String jwtToken) {
	
		try {
			Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken);
			return true;
		}
		catch(Exception e) {
			logger.error("token is not validated {}",e.getMessage());
		}
		return false;
	}
	
	public String extractUsernameFromJwtToken(String jwtToken) {
		String username = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken).getBody().getSubject();
		return username;
	}
	
	
}
