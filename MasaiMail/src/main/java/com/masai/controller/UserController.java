package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.EmailException;
import com.masai.model.RecivedMessage;
import com.masai.model.SentMessage;
import com.masai.model.StarredMessage;
import com.masai.model.User;

import com.masai.service.UserService;

@RestController
@RequestMapping("/masaimail")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user){
		
		User saveduser = userService.registerUser(user);
		
		return new ResponseEntity<User>(saveduser, HttpStatus.CREATED);
	}
	
	@GetMapping("/getuser/{email}")
	public ResponseEntity<User> getUserByEmailHandler(@PathVariable String email) throws EmailException{
		
		User user = userService.getUserByEmail(email);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/mail")
	public ResponseEntity<User> sendMailHandler( @RequestBody SentMessage _message) throws EmailException{
		
		User user = userService.sendEmail(_message);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/recived/{id}")
	public ResponseEntity<User> starRecivedMessageHandler(@PathVariable Integer id) throws EmailException{
		User user = userService.starRecivedMessage(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/sent/{id}")
	public ResponseEntity<User> starSentMessageHandler(@PathVariable Integer id) throws EmailException{
		User user = userService.starSentMessage(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/recived/{id}")
	public ResponseEntity<User> deleteRecivedMessageHandler( @PathVariable Integer id) throws EmailException{
		User user = userService.deleteFromRecivedMessage(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/starred/{id}")
	public ResponseEntity<User> deleteStarredMessageHandler( @PathVariable Integer id) throws EmailException{
		User user = userService.deleteFromStarredMessage(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/sent/{id}")
	public ResponseEntity<User> deleteSentMessageHandler( @PathVariable Integer id) throws EmailException{
		User user = userService.deleteFromSentMessage(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/mail")
	public ResponseEntity<List<RecivedMessage>> getAllMessageHandler() throws EmailException{
		
		List<RecivedMessage> messages = userService.getAllMessages();
		
		return new ResponseEntity<List<RecivedMessage>>(messages,HttpStatus.OK);
	}
	
	@GetMapping("/starred")
	public ResponseEntity<List<StarredMessage>> getAllStarredMessageHandler(@PathVariable String mail) throws EmailException{
		
		List<StarredMessage> messages = userService.getAllStarredMessages();
		
		return new ResponseEntity<List<StarredMessage>>(messages,HttpStatus.OK);
	}
	
	@PostMapping("/login/{email}/{password}")
	 public ResponseEntity<User> loginHandler(@PathVariable String email, @PathVariable String password) throws EmailException{
		
		User user = userService.LoginUser(email, password);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUserHandler(@RequestBody User user) throws EmailException{
		User myuser = userService.updateUser(user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	
	
	
	
	

}
