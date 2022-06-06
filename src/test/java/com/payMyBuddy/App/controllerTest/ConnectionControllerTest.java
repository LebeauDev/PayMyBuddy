package com.payMyBuddy.App.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.payMyBuddy.App.Service.ConnectionService;
import com.payMyBuddy.App.controller.ConnectionController;

import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.ConnectionForm;
import com.payMyBuddy.App.repository.ConnectionRepository;
import com.payMyBuddy.App.repository.UserRepository;


@SpringBootTest
public class ConnectionControllerTest {
	
	@Autowired
	private ConnectionController conctr;
	

	@Autowired
	private ConnectionService servRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	ConnectionRepository conRepo;
	
	
	@Mock
	private Model model;
	
	// avant de lancer tests, se connecter
	
	@WithUserDetails("mail@test.de")
	@Test
	public void addConnectionTest() {
		

		
		// recupere listeUtilsateur d'utilisateur de cet email avec uRepo
		
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User userCurrent = uRepo.findByEmail(authentication.getName()).get(0);	
		
		List<Connection> listConnectionInitiale = conRepo.findByIdemetteur(userCurrent.getId());
		
		//test
		
		ConnectionForm conForm = new ConnectionForm();
		
		conForm.setEmail("ml@gmail.fr");
		
		
		
		conctr.addConnection(model, conForm);
		
		List<Connection> listConnectionFinale = conRepo.findByIdemetteur(userCurrent.getId());
		
		assertEquals( listConnectionInitiale.size()+1,  listConnectionFinale.size());
		
		//return "redirect:/showTransaction";
	}

}
