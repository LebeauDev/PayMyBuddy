package com.payMyBuddy.App.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;

import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.form.ConnectionForm;

@SpringBootTest
public class UserControllerTest {
	
	@WithUserDetails("mail@test.de")
	@Test
	public void loginTest() {
		

		
		// recupere listeUtilsateur d'utilisateur de cet email avec uRepo
		
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		assertEquals( "mail@test.de",  authentication.getName());
		
		//return "redirect:/showTransaction";
	}

}
