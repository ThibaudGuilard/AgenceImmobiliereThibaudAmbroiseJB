package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class LoginAgent {

	@Email
	private String email;
	
	private String pwd;
}
