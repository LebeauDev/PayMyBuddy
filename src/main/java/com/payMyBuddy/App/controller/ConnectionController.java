package com.payMyBuddy.App.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payMyBuddy.App.Service.ConnectionService;
import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.Transaction;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.ConnectionForm;
import com.payMyBuddy.App.form.TransactionForm;
import com.payMyBuddy.App.repository.ConnectionRepository;
import com.payMyBuddy.App.repository.UserRepository;

@Controller
public class ConnectionController {

	@Autowired
	private ConnectionService servRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	 
	
	@GetMapping({"/addConnection"})
	public ModelAndView showUsers() {
		ModelAndView mav = new ModelAndView("add-connection");
		
		
		ConnectionForm connectionForm = new ConnectionForm();
		mav.addObject("connectionForm",connectionForm);
		
		return mav;
	}
	
	@RequestMapping(value = { "/addConnection" }, method = RequestMethod.POST)
	public String addConnection(Model model, 
			@ModelAttribute("connectionForm") ConnectionForm connectionForm) {
		
		//recupere email
		String email = connectionForm.getEmail();
		
		// recupere listeUtilsateur d'utilisateur de cet email avec uRepo
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User userCurrent = uRepo.findByEmail(authentication.getName()).get(0);		
		
		servRepo.newConnection(email, userCurrent);
		
		return "redirect:/showTransaction";
	}

}
