package com.payMyBuddy.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.App.entity.Transaction;
import com.payMyBuddy.App.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Query("SELECT t.montant FROM transaction t where emetteur = :id") 
    List<String> myFindByEmail(@Param("id") String id);
	
	List<Transaction> findByEmetteur(@Param("emetteur") int emetteur);


}
