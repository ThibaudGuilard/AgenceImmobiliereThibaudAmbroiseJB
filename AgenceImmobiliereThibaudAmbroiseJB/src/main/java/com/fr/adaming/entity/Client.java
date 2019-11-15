package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fr.adaming.entity.enume.TypeClient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Thibaud
 *
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Client extends User{
	
	@Column(nullable = true)
	private TypeClient type;
	
	@ManyToOne @JoinColumn(name = "id_agent")
	private Agent agent;
	
	@OneToMany(mappedBy = "clients")
	private List<Bien> biens;

	public Client(String email, String fullName, int telephone, Enum<TypeClient> type, Agent agent, List<Bien> biens) {		
	}
	
	public Client(long id,String email, String fullName, int telephone, Enum<TypeClient> type, Agent agent, List<Bien> biens) {
	}

	
}
