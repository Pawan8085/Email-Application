package com.masai.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{


	private static final long serialVersionUID = 1L;
	private User user;
	
	
	public SecurityUser(User user) {
		this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
//			List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//	
//	        grantedAuthorityList.add(new SimpleGrantedAuthority(employee.getRole()));
//	
//	        return grantedAuthorityList;
		return null;
	}
	
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		
		return true;
	}
}
