package com.payMyBuddy.App.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.App.entity.Compte;
import com.payMyBuddy.App.entity.Transaction;
import com.payMyBuddy.App.repository.CompteRepository;
import com.payMyBuddy.App.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository tRepo;
	
	@Autowired
	private CompteRepository coRepo;

	public TransactionRepository gettRepo() {
		return tRepo;
	}
	
	
	
	
	
	public String createTransaction(int id, float montant, String destinataire, String description) {
		List<Compte> listCompteEmetteur = coRepo.findByIdutilisateur(id);
		
		
		if(montant>listCompteEmetteur.get(0).getSolde()) {
			return "redirect:/showTransaction?error=1";
		}
		
		
		List<Compte>listCompteDestinataire = coRepo.findByIdutilisateur(Integer.parseInt(destinataire.split("-")[0]));
		listCompteDestinataire.get(0).setSolde(listCompteDestinataire.get(0).getSolde()+ montant);
		
		listCompteEmetteur.get(0).setSolde(listCompteEmetteur.get(0).getSolde()-montant);

		Transaction t = new Transaction();
		t.setDestinataire(Integer.parseInt(destinataire.split("-")[0]));
		t.setNom(destinataire.split("-")[1]);
		t.setMontant(montant);
		t.setDescription(description);
		
		
		t.setEmetteur(id);
		t.setDate(new Date());
		
		tRepo.save(t);
		coRepo.save(listCompteDestinataire.get(0));
		coRepo.save(listCompteEmetteur.get(0));
		
		return "redirect:/showTransaction";
		
	}
	
	
}
