package com.masai.service;

import java.util.List;

import com.masai.exceptions.EmailException;
import com.masai.model.RecivedMessage;
import com.masai.model.SentMessage;
import com.masai.model.StarredMessage;
import com.masai.model.User;

public interface UserService {
	
	User registerUser(User user);
	User getUserByEmail(String email)throws EmailException;
	
	User sendEmail(SentMessage message)throws EmailException;
	
	User starRecivedMessage(Integer messageId)throws EmailException;
	
	User starSentMessage(Integer messageId)throws EmailException;
	
	User deleteFromRecivedMessage(Integer messageId)throws EmailException;
	
	User deleteFromStarredMessage(Integer messageId)throws EmailException;
	
	User deleteFromSentMessage(Integer messageId)throws EmailException;
	
	List<RecivedMessage> getAllMessages()throws EmailException;
	
	List<StarredMessage> getAllStarredMessages()throws EmailException;
	
	User LoginUser()throws EmailException;
	
	User updateUser(User user)throws EmailException;
}
