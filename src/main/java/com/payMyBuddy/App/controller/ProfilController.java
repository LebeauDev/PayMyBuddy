package com.payMyBuddy.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.ConnectionForm;
import com.payMyBuddy.App.repository.UserRepository;


@Controller
public class ProfilController {
	
	@Autowired
	UserRepository uRepo;

	
		
		// modelAndView mav
		@GetMapping({"/profile"})
		public ModelAndView showUsers() {
			ModelAndView mav = new ModelAndView("profile");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email =  authentication.getName();
			
			User userCurrent = uRepo.findByEmail(email).get(0); 
			mav.addObject("userCurrent",userCurrent);
			
			return mav;
		}
		//Recup userCurrnent
		// envoie userCurnent à mav
		// dans profile.html afficher les infos de userCurrent
		
	}


