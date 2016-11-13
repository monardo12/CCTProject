package com.cct.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("user").password("123456").roles("ROLE_USER");
	  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ROLE_ADMIN");
	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("ROLE_DBA");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		.antMatchers("/cliente/**").access("hasRole('ROLE_USER')")
		.antMatchers("/cotizacion/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/inventario/**").access("hasRole('ROLE_DBA')")
		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
		.and().formLogin();

	}
}