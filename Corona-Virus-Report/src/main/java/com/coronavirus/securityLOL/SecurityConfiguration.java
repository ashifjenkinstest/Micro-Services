package com.coronavirus.securityLOL;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
		
		managerBuilder.inMemoryAuthentication()
		.withUser("blah")
		.password("blah")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		 http.authorizeRequests()
	      .anyRequest().authenticated();
	}
	
	


}
