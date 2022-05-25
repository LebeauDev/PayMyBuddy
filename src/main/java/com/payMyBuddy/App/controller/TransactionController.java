package com.payMyBuddy.App.controller;

import java.util.Date;
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

import com.payMyBuddy.App.Service.CompteService;
import com.payMyBuddy.App.Service.ConnectionService;
import com.payMyBuddy.App.Service.TransactionService;
import com.payMyBuddy.App.entity.Compte;
import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.Transaction;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.TransactionForm;
import com.payMyBuddy.App.form.UserForm;
import com.payMyBuddy.App.repository.CompteRepository;
import com.payMyBuddy.App.repository.ConnectionRepository;
import com.payMyBuddy.App.repository.TransactionRepository;
import com.payMyBuddy.App.repository.UserRepository;

@Controller
public class TransactionController {
	@Autowired
	private TransactionService transService;
	
	@Autowired
	private ConnectionService conService;
	
	@Autowired
	private CompteService coServ;
	
	@Autowired
	UserRepository uRepo;
	
	@GetMapping({"/showTransaction"})
	public ModelAndView showUsers(HttpSession session) {
		ModelAndView mav = new ModelAndView("list-transactions");
		//List<Transaction> listTransaction = tRepo.findAll();
		int id = -1;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email =  authentication.getName();
		
		//System.out.println("@@@@@@@@@@@@@@@@@@@"+ email);
		
		User userCurrent = uRepo.findByEmail(email).get(0);
		
		if(userCurrent!=null) {
			
			id = userCurrent.getId();
		}
		
		List<Transaction> listTransaction = transService.gettRepo().findByEmetteur(id);
		List<Connection> listConnection = conService.getcRepo().findByIdemetteur(id);
		
		//List<Connection> listConnection = cRepo.findAll();
		//System.out.println(listConnection.get(1).getIdDestinataire());
		mav.addObject("transactions", listTransaction);
		mav.addObject("connections", listConnection);
		TransactionForm transactionForm = new TransactionForm();
		mav.addObject("transactionForm",transactionForm);
		return mav;
	}
	
	@RequestMapping(value = { "/showTransaction" }, method = RequestMethod.POST)
	public String saveTransaction(Model model,
			@ModelAttribute("transactionForm") TransactionForm transactionForm) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User userCurrent = uRepo.findByEmail(authentication.getName()).get(0);

		String destinataire  = transactionForm.getDestinataire();
		Float montant = transactionForm.getMontant();
		String description = transactionForm.getDescription();
		int id = -1;
		if(userCurrent!=null) {
			
			id = userCurrent.getId();
		}
		
		if(montant<0) {
			return "redirect:/showTransaction?error=1";
		}
		
		
		
		
		
		
		
		String resultat = transService.createTransaction(id, montant, destinataire, description);
		
		return resultat;
		
		
	}

}
