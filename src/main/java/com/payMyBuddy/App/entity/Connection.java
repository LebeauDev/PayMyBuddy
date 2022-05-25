package com.payMyBuddy.App.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "connection")
@Table(name = "connection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int idemetteur;
	
	private int iddestinataire;
	
	private String nom;
	
	
	public int getIdDestinataire() {
		return iddestinataire;
	}
	public void setIdDestinataire(int idDestinataire) {
		this.iddestinataire = idDestinataire;
	}
	public int getIdEmetteur() {
		return idemetteur;
	}
	public void setIdEmetteur(int idEmetteur) {
		this.idemetteur = idEmetteur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
