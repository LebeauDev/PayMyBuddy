package com.payMyBuddy.App.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "compte")
@Table(name = "compte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcompte;
	private double solde;
	private int idutilisateur;
	
	
	
	public int getIdcompte() {
		return idcompte;
	}
	public void setIdcompte(int idcompte) {
		this.idcompte = idcompte;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public int getIdutilisateur() {
		return idutilisateur;
	}
	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}

}
