package com.training.springbootlogin.service;

import com.training.springbootlogin.config.SecurityConfigProperties;
import com.training.springbootlogin.config.SuperUserConfig;
import com.training.springbootlogin.config.TestUserConfig;
import com.training.springbootlogin.dto.AuthenticationRequest;
import com.training.springbootlogin.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoginService implements AuthenticationProvider {
	
	@Autowired
	private SecurityConfigProperties config;
	
	@Autowired
	public LoginService(SuperUserConfig su, TestUserConfig tu) {
		superUser = new UserDto(su.getEmail(), su.getRoles());
		testUser = new UserDto(tu.getEmail(), tu.getRoles());
		testUserA = new AuthenticationRequest(tu.getEmail(), tu.getPassword());
	}
	
	// JSON Web Token generator
	public String generateToken(UserDto userDetails) {
		Map<String, Object> claims = new HashMap<>();
		long epochTime = System.currentTimeMillis();
		byte[] bytes = config.getSecret().getBytes();
		SignatureAlgorithm alg = SignatureAlgorithm.HS256;
		
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(userDetails.getEmail())
			.claim(config.getAuthoritiesClaim(), AuthorityUtils.authorityListToSet(
					conversionStringAuthorities(userDetails.getRoles())))
			.setIssuedAt(new Date(epochTime))
			.setExpiration(new Date(epochTime + config.getExpiresIn()))
			.signWith(new SecretKeySpec(bytes, alg.getJcaName()), alg)
			.compact();
	}
	
	public String generateToken() {
		return generateToken(userFound);
	}
	
	private List<GrantedAuthority> conversionStringAuthorities(List<String> roles) {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	private final UserDto superUser, testUser;
	private final AuthenticationRequest testUserA;
	
// TODO **** Provisional response with testUser ****
	public Mono<UserDto> loadUser(AuthenticationRequest request) {
		return request.equals(testUserA)
				? Mono.just(testUser)
				: Mono.error(new UsernameNotFoundException("Unknown user \'" + testUserA.getEmail()  + "\'"));
	}
	
	private UserDto userFound;
	
	// Authentication provider
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authenticate(new AuthenticationRequest(
				(String)authentication.getPrincipal(), (String)authentication.getCredentials()));
	}
	
	public Authentication authenticate(AuthenticationRequest request) throws AuthenticationException {
		Optional<String> username = Optional.ofNullable((String)request.getEmail());
		Optional<String> password = Optional.ofNullable((String)request.getPassword());

		if(credentialsMissing(username, password) || !credentialsValid(request))
			throw new BadCredentialsException("Invalid credentials");
		
		return new UsernamePasswordAuthenticationToken(username.get(), null,
				conversionStringAuthorities(userFound.getRoles()));
	}
	
	private boolean credentialsMissing(Optional<String> username, Optional<String> password) {
		return !username.isPresent() || !password.isPresent();
	}
	
	private boolean credentialsValid(AuthenticationRequest request) { // Request to DB
		loadUser(request).subscribe(user -> userFound = user, t -> userFound = null);
		
		return userFound != null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}