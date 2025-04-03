package com.training.springbootlogin.config;

import com.training.springbootlogin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private LoginService provider;
	
	@Autowired
	private SecurityConfigProperties config;
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(provider);
	}

	/**
	 * Manejo de autenticación básica recibiendo JSON con user y password admin
	 * @param http the {@link HttpSecurity} to modify
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.addFilterBefore(simpleAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers(HttpMethod.POST, config.getSignUpUrl()).permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(new BasicAuthenticationEntryPoint() {
			
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().print(config.getErr());//mostrado de mensaje configurado
			}
			
		});
	}
	
}