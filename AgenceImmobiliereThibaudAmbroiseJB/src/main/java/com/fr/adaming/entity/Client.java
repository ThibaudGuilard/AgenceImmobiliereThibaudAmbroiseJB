package com.fr.adaming.entity;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Client extends User{
	
	@Column(nullable = false)
	private Enum<TypeClient> type;

	public Client(String email, String fullName, int telephone, Enum<TypeClient> type) {
		super();
		this.type = type;
	}

	
}
