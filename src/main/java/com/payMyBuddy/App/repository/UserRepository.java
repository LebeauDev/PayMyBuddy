package com.payMyBuddy.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.App.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

	
	List<User> findByEmail( String email);

	User getById(int id);
	
	
}
