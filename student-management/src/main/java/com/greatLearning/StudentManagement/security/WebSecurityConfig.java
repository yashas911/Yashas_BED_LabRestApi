package com.greatLearning.StudentManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.StudentManagement.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsServiceImpl getuserUserDetailsServiceImpl() {
		UserDetailsServiceImpl impl = new UserDetailsServiceImpl();
		return impl;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider authBuilder = new DaoAuthenticationProvider();
		authBuilder.setUserDetailsService(getuserUserDetailsServiceImpl());
		authBuilder.setPasswordEncoder(passwordEncoder());
		return authBuilder;
	}

	public void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/", "/student/save", "/student/showFormForAdd", "/student/403")
				.hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/student/showFormForUpdate", "/student/delete")
				.hasAuthority("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list")
				.permitAll()
				.and().logout().logoutSuccessUrl("/login").permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/student/403").and().cors().and().csrf().disable();
		}
}
