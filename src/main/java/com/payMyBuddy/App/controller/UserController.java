package com.payMyBuddy.App.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;

import org.hibernate.resource.transaction.internal.SynchronizationRegistryStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payMyBuddy.App.Service.UserService;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.UserForm;
import com.payMyBuddy.App.repository.UserRepository;

@Controller
public class UserController {


	@GetMapping("/loginPage")
	public String login() {

		return "login";
	}
	
	

}
