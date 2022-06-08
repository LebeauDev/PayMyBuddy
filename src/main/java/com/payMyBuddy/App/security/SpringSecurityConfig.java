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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests().antMatchers("/**","/loginPage", "/list","/").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/loginPage")
				.defaultSuccessUrl("/showTransaction").failureUrl("/loginPage?error=true").permitAll()
				.and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/loginPage"); 
		

	}
	
	
	 @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userDetailsService).passwordEncoder(passwordEncoder());
    }
	 
	 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}