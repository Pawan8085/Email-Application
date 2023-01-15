package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.RecivedMessage;

public interface RecivedMessageRepository extends JpaRepository<RecivedMessage, Integer>{
	
	void deleteByEmail(String email);

}
