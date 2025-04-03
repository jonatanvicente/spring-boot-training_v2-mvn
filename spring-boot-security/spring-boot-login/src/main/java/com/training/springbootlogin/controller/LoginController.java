package com.training.springbootlogin.controller;

import com.training.springbootlogin.dto.AuthenticationRequest;
import com.training.springbootlogin.dto.AuthenticationResponse;
import com.training.springbootlogin.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/springboottraining/api/v1")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/test")
	public String test() {
		log.info("** Saludos desde el logger **");
		
		return "Hello!!!";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			loginService.authenticate(authenticationRequest);
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		String jwt = loginService.generateToken();
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}