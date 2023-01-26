package com.masai.model;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
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
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	
	private String mobileNumber;
	
	 @JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	
	@OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<RecivedMessage> recivedMessage;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<SentMessage> sentMessage;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<StarredMessage> starredMessage;
	
	
	
	
}
