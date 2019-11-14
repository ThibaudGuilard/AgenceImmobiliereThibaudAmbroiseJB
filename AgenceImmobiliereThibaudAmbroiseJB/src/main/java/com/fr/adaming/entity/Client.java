package com.fr.adaming.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Client extends User{
	
	private Enum<TypeClient> type;

	public Client(String email, String fullName, int telephone, Enum<TypeClient> type) {
		super();
		this.type = type;
	}

	
}
