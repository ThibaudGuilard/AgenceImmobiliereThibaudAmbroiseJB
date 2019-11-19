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

	public Client(String email, String fullName, String telephone, Enum<TypeClient> type, Agent agent, List<Bien> biens) {
		super (email, fullName, telephone);
		this.type=(TypeClient) type;
		this.agent=agent;
		this.biens=biens;	
	}
	
	public Client(long id,String email, String fullName, String telephone, Enum<TypeClient> type, Agent agent, List<Bien> biens) {
	
		super (id, email, fullName, telephone);
		this.type=(TypeClient) type;
		this.agent=agent;
		this.biens=biens;
	}

	public Client(String email, String fullName, String telephone, boolean deleted, TypeClient type) {
		super(email, fullName, telephone, deleted);
		this.type = type;
	}

	public Client(String email, String fullName, String telephone, boolean deleted, TypeClient type, Agent agent,
			List<Bien> biens) {
		super(email, fullName, telephone, deleted);
		this.type = type;
		this.agent = agent;
		this.biens = biens;
	}
	
}
