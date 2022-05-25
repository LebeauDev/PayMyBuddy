package com.payMyBuddy.App.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.repository.ConnectionRepository;
import com.payMyBuddy.App.repository.UserRepository;
@Controller
public class ContactController {
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	ConnectionRepository conRepo;
	
	@GetMapping("/contact")
	public ModelAndView showUsers() {
		ModelAndView mav = new ModelAndView("contact");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User userCurrent = uRepo.findByEmail(authentication.getName()).get(0);
		
		List<Connection> listConnection = conRepo.findByIdemetteur(userCurrent.getId());
		
		List<User> userList = new ArrayList<User>();
		
		for(Connection connection :  listConnection ) {
			int id = connection.getIdDestinataire();
			User user = uRepo.getById(id);
			userList.add(user);
		}
		
		
		mav.addObject("connections", userList);

		return mav;
	}

}
