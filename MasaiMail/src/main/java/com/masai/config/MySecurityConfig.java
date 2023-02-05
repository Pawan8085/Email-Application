package com.masai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {
	
	@Bean
	public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
	
	
		http.authorizeHttpRequests( (auth)->auth
				.requestMatchers("/mymail/mail","/mymail/starred", 
						"/mymail/login/*/*", "/mymail/getuser/**", 
						"/mymail/recived/**", "/mymail/sent/**" ,
						"/mymail/starred/**","/mymail/user")
				        .authenticated()
						.requestMatchers("/mymail/register").permitAll()
				
		).csrf().disable()
		.httpBasic();
	
		return http.build();
}

	@Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }

}
