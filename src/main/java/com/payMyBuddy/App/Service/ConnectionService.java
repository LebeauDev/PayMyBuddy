package com.payMyBuddy.App.Service;

import java.beans.JavaBean;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.User;
import com.payMyBuddy.App.repository.ConnectionRepository;
import com.payMyBuddy.App.repository.UserRepository;


@Service
public class ConnectionService {
	@Autowired
	private ConnectionRepository cRepo;
	
	@Autowired
	private UserRepository uRepo;

	public ConnectionRepository getcRepo() {
		return cRepo;
	}
	
	public void newConnection(String email,User userCurrent) {
		
		// listeUtilsateur.get(0) pour avoir l'utilisateur à ajouter
		
		
		//User userCurrent = uRepo.findByEmail(emailBDD).get(0);
		//recupere id utilisateur session
		int id = -1;
		String currentEmail = "";
		
		if(userCurrent!=null) {
			//System.out.println(((User)session.getAttribute("user")).getId());
			id = userCurrent.getId();
			 currentEmail = userCurrent.getEmail();
		}
		
		//creer une nouvelle connextion  con
		
		if (email.equals( currentEmail)) {
			return;
		}
		
		Connection con = new Connection();
		// con.setIdEmetteur(id)...
		List<User> listUtilisateur = uRepo.findByEmail(email);
		con.setIdDestinataire(listUtilisateur.get(0).getId());
		con.setIdEmetteur(id);
		con.setNom(listUtilisateur.get(0).getNom());
		//conRepo.save(con)
		
		cRepo.save(con);
	}
	
	


}
