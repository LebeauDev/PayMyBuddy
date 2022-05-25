package com.payMyBuddy.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

	@GetMapping("/profile")
	public String home() {
		
		// modelAndView mav
		//Recup userCurrnent
		// envoie userCurnent à mav
		// dans profile.html afficher les infos de userCurrent
		return "profile";
	}

}
