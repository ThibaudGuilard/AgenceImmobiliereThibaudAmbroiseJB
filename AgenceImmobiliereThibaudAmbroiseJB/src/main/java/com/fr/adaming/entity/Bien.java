// Ambroise RENE

package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter 
@NoArgsConstructor
public class Bien {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false )
	private double prix;
	
	private boolean vendu;
	
	private boolean deleted;
	
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client clients;

	public Bien(long id, double prix, boolean vendu, boolean deleted, Client clients) {
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
		this.clients = clients;
	}

	public Bien(double prix, boolean vendu, boolean deleted, Client clients) {
		super();
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
		this.clients = clients;
	}

	public Bien(double prix, boolean vendu, boolean deleted) {
		super();
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}

	public Bien(double prix, boolean vendu) {
		super();
		this.prix = prix;
		this.vendu = vendu;
	}
	public Bien(long id, double prix, boolean vendu, boolean deleted) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}
	
	
}
