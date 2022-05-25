package com.payMyBuddy.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.payMyBuddy.App.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	List<Compte> findByIdutilisateur(@Param("idutilisateur") int idutilisateur);

}
