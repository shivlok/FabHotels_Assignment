package com.example.wallet.SpringSecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.wallet.ServiceImpl.UserDetailsImpl;
import com.example.wallet.ServiceImpl.UserDetailsServiceImplement;
import com.example.wallet.services.JwtHelperService;

public class RequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsServiceImplement userDetailsServiceImplement;
	
	@Autowired
	private JwtHelperService jwtHelperService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwtToken=getJwtTokenFromReq(request);
			if(jwtToken != null && jwtHelperService.validateJwtToken(jwtToken)) {
				String username= jwtHelperService.extractUsernameFromJwtToken(jwtToken);
				UserDetails userDetails= userDetailsServiceImplement.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
			
		}
		catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);
		
		
		
	}
	
	private String getJwtTokenFromReq(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			String[] splitHeader = header.split(" ");
			return splitHeader[1];
		}
		return null;
	}

}
