package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private String email;
	
	private String firstName;
	
	
	private String lastName;
	

	private String password;
	
	
	private String mobileNumber;
	
	 @JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<RecivedMessage> recivedMessage;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<SentMessage> sentMessage;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<StarredMessage> starredMessage;
	
	
	
	
}
