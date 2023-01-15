package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.StarredMessage;

public interface StarredMessageRespository extends JpaRepository<StarredMessage, Integer>{
	
	void deleteByEmail(String email);
	
	 List<StarredMessage> findByEmailAndMessage(String email, String message);
}
