package com.fr.adaming.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class User {
	
	private long id;
	private String email;
	private String fullName;
	private int telephone;

}
