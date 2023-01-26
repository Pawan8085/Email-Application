package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exceptions.EmailException;
import com.masai.model.RecivedMessage;
import com.masai.model.SentMessage;
import com.masai.model.StarredMessage;
import com.masai.model.User;
import com.masai.repository.RecivedMessageRepository;
import com.masai.repository.SentMessageRespository;
import com.masai.repository.StarredMessageRespository;
import com.masai.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecivedMessageRepository recivedMessageRepository;
	
	@Autowired
	private StarredMessageRespository starredMessageRespository;
	
	@Autowired
	private SentMessageRespository sentMessageRespository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	@Override
	public User getUserByEmail(String email) throws EmailException {
		
		Optional<User> opt = userRepository.findById(email);
		
		if(opt.isPresent()) {
			User user = opt.get();
			System.out.println(user);
			return user;
		}
		else throw new EmailException("Invalid email - "+email);
	}
	@Override
	public User sendEmail(SentMessage _message) throws EmailException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
	
		Optional<User> opt = userRepository.findById(userName);
		
			User user = opt.get();
			user.getSentMessage().add(_message);
			_message.setUser(user);
			sentMessageRespository.save(_message);
			String mail= _message.getEmail();
			
			Optional<User> user_ = userRepository.findById(mail);
			
			if(user_.isPresent()) {
				User user__ = user_.get();
				
				RecivedMessage r_message= new RecivedMessage();
				r_message.setMessage(_message.getMessage());
				r_message.setEmail(userName);
				
				user__.getRecivedMessage().add(r_message);
				r_message.setUser(user__);
				
				recivedMessageRepository.save(r_message);
				
				return user;
			}
			else throw new EmailException("Invalid email id");
				
	}
	
	@Override
	public User starRecivedMessage(Integer messageId) throws EmailException {
		
			Optional<RecivedMessage> rmopt= recivedMessageRepository.findById(messageId);
			if(rmopt.isPresent()) {
				
				
				RecivedMessage rm = rmopt.get();
				
				//getting the user
				User user = rm.getUser();
				
				//*avoiding adding duplicate 
				List<StarredMessage> messages = starredMessageRespository.findByEmailAndMessage(rm.getEmail(), rm.getMessage());
				
				if(messages.size()==0) {
					
					StarredMessage smessage = new StarredMessage();
					smessage.setEmail(rm.getEmail());
					smessage.setMessage(rm.getMessage());
					smessage.setMessageType("Recived");
					user.getStarredMessage().add(smessage);
					smessage.setUser(user);
					
					 starredMessageRespository.save(smessage);
				}
				else {
					String currentUser = messages.get(0).getUser().getEmail();
					
					System.out.println(currentUser);
					
					if(!user.getEmail().equals(currentUser)) {
						StarredMessage smessage = new StarredMessage();
						smessage.setEmail(rm.getEmail());
						smessage.setMessage(rm.getMessage());
						smessage.setMessageType("Recived");
						user.getStarredMessage().add(smessage);
						smessage.setUser(user);
						
						 starredMessageRespository.save(smessage);
					}
				}
				
				
				
				 return user;
			}
			else throw new EmailException("Invalid message id ");
		
		
		
		
	}
	
	@Override
	public User starSentMessage(Integer messageId) throws EmailException {
		Optional<SentMessage> smopt= sentMessageRespository.findById(messageId);
		if(smopt.isPresent()) {
			
			
			SentMessage sm = smopt.get();
			
			//getting the user
			User user = sm.getUser();
			
			//*avoiding adding duplicate 
			List<StarredMessage> messages = starredMessageRespository.findByEmailAndMessage(sm.getEmail(), sm.getMessage());
			
			if(messages.size()==0) {
				
				StarredMessage smessage = new StarredMessage();
				smessage.setEmail(sm.getEmail());
				smessage.setMessage(sm.getMessage());
				smessage.setMessageType("Sent");
				user.getStarredMessage().add(smessage);
				smessage.setUser(user);
				
				 starredMessageRespository.save(smessage);
			}
			else {
				String currentUser = messages.get(0).getUser().getEmail();
				
				System.out.println(currentUser);
				
				if(!user.getEmail().equals(currentUser)) {
					StarredMessage smessage = new StarredMessage();
					smessage.setEmail(sm.getEmail());
					smessage.setMessage(sm.getMessage());
					smessage.setMessageType("Sent");
					user.getStarredMessage().add(smessage);
					smessage.setUser(user);
					
					 starredMessageRespository.save(smessage);
				}
			}
			
			
			
			 return user;
		}
		else throw new EmailException("Invalid message id ");
	
	}
	
	@Override
	public User deleteFromRecivedMessage(Integer messageId) throws EmailException {
	
			
			Optional<RecivedMessage> optRmessage = recivedMessageRepository.findById(messageId);
			
			if(optRmessage.isPresent()) {
				RecivedMessage message = optRmessage.get();
				
				User user = message.getUser();
				 recivedMessageRepository.delete(message);
				 return user;
				
			}
			else throw new EmailException("Invalid message id  : "+messageId);	
		
	}
	
	@Override
	public User deleteFromStarredMessage(Integer messageId) throws EmailException {
		// TODO Auto-generated method stub
		
		Optional<StarredMessage> optSMessage = starredMessageRespository.findById(messageId);
		
		if(optSMessage.isPresent()) {
			StarredMessage message = optSMessage.get();
			
			User user = message.getUser();
			
			starredMessageRespository.delete(message);
			
			return user;
				
		}
		else throw new EmailException("Invalid message id  : "+messageId);	
	}
	
	@Override
	public User deleteFromSentMessage(Integer messageId) throws EmailException {
		Optional<SentMessage> optSMessage = sentMessageRespository.findById(messageId);
		
		if(optSMessage.isPresent()) {
			SentMessage message = optSMessage.get();
			
			User user = message.getUser();
			
			sentMessageRespository.delete(message);
			
			return user;
				
		}
		else throw new EmailException("Invalid message id  : "+messageId);	
	}
	
	@Override
	public List<RecivedMessage> getAllMessages() throws EmailException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
	
		Optional<User> opt = userRepository.findById(userName);
		if(opt.isPresent()) {
			User user = opt.get();
			
			return user.getRecivedMessage();
		}
		else throw new EmailException("Invalid email id");
	}
	
	@Override
	public List<StarredMessage> getAllStarredMessages() throws EmailException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
	
		Optional<User> opt = userRepository.findById(userName);
		
		if(opt.isPresent()) {
			User user = opt.get();
			
			return user.getStarredMessage();
		}
		else throw new EmailException("Invalid email id");
	}
	
	@Override
	public User LoginUser(String email, String password) throws EmailException {
          Optional<User> opt = userRepository.findById(email);
		
		if(opt.isPresent()) {
			User user = opt.get();
			
			if(passwordEncoder.matches( password, user.getPassword())) {
				return user;
			}
			else throw new EmailException("Invalid password!");
			
			
		}
		else throw new EmailException("Invalid email - "+email);
		
	}
	
	@Override
	public User updateUser(User user) throws EmailException {
		return userRepository.save(user);
	}
	
	

}
