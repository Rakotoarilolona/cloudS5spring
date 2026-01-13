package com.carte.clouds5spring.controller;

import com.carte.clouds5spring.security.util.JwtUtil;
import com.carte.clouds5spring.dto.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) 
	{
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) 
	{
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		return jwtUtil.generateToken(request.getUsername());
	}
}
