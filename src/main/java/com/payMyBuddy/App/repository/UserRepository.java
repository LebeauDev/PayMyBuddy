package com.payMyBuddy.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.App.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//@Query("SELECT u.email FROM utilisateur u where email = :email") 
    //List<String> myFindByEmail(@Param("email") String email);
	
	//@Query("SELECT u.password FROM utilisateur u where email = :email") 
   // List<String> myGetPassByEmail(@Param("email") String email);
	
	//@Query("SELECT u FROM utilisateur u where email = :email") 
    //List<User> myGetUserByEmail(@Param("email") String email);
	
	List<User> findByEmail( String email);

	User getById(int id);
	
	//List<User> findByEmailOrUsername(String email);
	
}
