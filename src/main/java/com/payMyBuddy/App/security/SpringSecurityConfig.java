package com.payMyBuddy.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.payMyBuddy.App.Service.UserDetailsServiceImpl;
import com.payMyBuddy.App.Service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	 @Qualifier("userDetailsServiceImpl")
	 @Autowired
	 private UserDetailsService userDetailsService;


	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.print("############### config http");
		http.authorizeRequests().antMatchers("/loginPage", "/list","/").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/loginPage")
				.defaultSuccessUrl("/showTransaction").failureUrl("/loginPage?error=true").permitAll()
				.and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/loginPage"); 
		

	}
	
	
	 @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 //System.out.println("################ ICI AUTH");
        auth.userDetailsService( userDetailsService).passwordEncoder(passwordEncoder());
    }
	 
	 
    
	
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.print("############### config auth");
		auth.inMemoryAuthentication()
			.withUser("springuser").password(passwordEncoder().encode("spring123"))
			.roles("USER")
			.and()
			.withUser("springadmin").password(passwordEncoder().encode("admin123"))
			.roles("ADMIN", "USER");
	
	}
	
		/*
	// create two users, admin and user
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

	}
	*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}