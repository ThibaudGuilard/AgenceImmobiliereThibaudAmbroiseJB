package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fr.adaming.entity.enume.TypeClient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Client extends User{
	
	private Enum<TypeClient> type;
	@ManyToOne @JoinColumn(name = "id_agent")
	private Agent agent;
	@OneToMany(mappedBy = "client")
	private List<Bien> biens;

	public Client(String email, String fullName, int telephone, Enum<TypeClient> type) {
		super();
		this.type = type;
	}

	
}
