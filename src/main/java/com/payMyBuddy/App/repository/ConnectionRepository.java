package com.payMyBuddy.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.payMyBuddy.App.entity.Connection;
import com.payMyBuddy.App.entity.Transaction;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
	
	List<Connection> findByIdemetteur(@Param("idemetteur") int idEmetteur);

}
