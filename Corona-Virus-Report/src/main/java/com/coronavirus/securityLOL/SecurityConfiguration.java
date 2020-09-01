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
		.roles("ADMIN")
		.and()
		.withUser("admin1")
		.password("admin1")
		.roles("ADMIN1");
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")// The admin api can only be accessed by the users with admin role
        .antMatchers("/future").hasRole("USER")
            .antMatchers("/**").hasAnyRole("USER","ADMIN","ADMIN1")// All the other api can be accessed by  any other role
          //  .antMatchers("/future").hasRole("USER")// The future api can only be accessed by the users with user role
            /*Here the order in which roles are defined matters, in this case we allow all the apis to be accessible by all the users
             * first then we set the future api to be only accessible by user 
             * so it has no effect*/
            .and()
            .formLogin()
            ;
	}
	
	
	


}
