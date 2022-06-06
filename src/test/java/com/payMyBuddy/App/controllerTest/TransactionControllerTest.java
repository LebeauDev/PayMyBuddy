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

import com.payMyBuddy.App.controller.TransactionController;
import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.Transaction;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.ConnectionForm;
import com.payMyBuddy.App.form.TransactionForm;
import com.payMyBuddy.App.repository.TransactionRepository;
import com.payMyBuddy.App.repository.UserRepository;


@SpringBootTest
public class TransactionControllerTest {
	@Mock
	private Model model;
	
	@Autowired
	UserRepository uRepo;
	@Autowired
	TransactionRepository transRepo;
	@Autowired
	TransactionController transCon;
	@WithUserDetails("mail@test.de")
	@Test
	public void saveTransactionTest() {
		

		
		// recupere listeUtilsateur d'utilisateur de cet email avec uRepo
		
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User userCurrent = uRepo.findByEmail(authentication.getName()).get(0);	
		
		List<Transaction>listTransaction = transRepo.findByEmetteur(userCurrent.getId());
		 
		
		
		//test
		
		TransactionForm transForm = new TransactionForm();
		
		transForm.setDescription("testSpring");
		transForm.setMontant(10);
		transForm.setDestinataire("2-Lebeau");
		
		transCon.saveTransaction(model, transForm);
		
		List<Transaction>listTransactionFinale = transRepo.findByEmetteur(userCurrent.getId());
		
		
		assertEquals( listTransaction.size()+1,  listTransactionFinale.size());
		
		//return "redirect:/showTransaction";
	}

}
