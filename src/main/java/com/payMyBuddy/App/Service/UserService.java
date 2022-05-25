package com.payMyBuddy.App.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;

@Service
public class UserService{ 
	@Autowired
	private UserRepository uRepo;

	public UserRepository getuRepo() {
		return uRepo;
	}
	
	public String login(String email, String password, HttpSession session) {
		List<User> users = uRepo.findByEmail(email);
		//List<String> emails = uRepo.myFindByEmail(email);
		
		//System.out.println(users.get(0).getEmail());
		
		//if(email.equals(users.get(0))) {
		try {
			//if(emails.get(0).equals(email)) {
			if(users.get(0).getEmail().equals(email)) {
				
				//List<String> passs = uRepo.myGetPassByEmail(email);
				//System.out.println(passs.toString());
				//if(passs.get(0).equals(password)) {
				if(users.get(0).getPassword().equals(password)) {
					session.setAttribute("user", users.get(0));
					return "redirect:/showTransaction";
				}
				return "login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "login";
		}
		
		return "login";
	}

	public User findByEmail(String username) {
		// TODO Auto-generated method stub
		return uRepo.findByEmail(username).get(0);
	}
	
	

}
