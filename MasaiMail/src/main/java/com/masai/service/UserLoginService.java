package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.model.SecurityUser;
import com.masai.model.User;
import com.masai.repository.UserRepository;


@Service
public class UserLoginService implements UserDetailsService{
	
	
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		Optional<User> userOpt= userRepository.findById(username);
		
		if(userOpt.isPresent())
			return new SecurityUser(userOpt.get());
		else
			throw new UsernameNotFoundException("Invalid user name!");
	}

}
