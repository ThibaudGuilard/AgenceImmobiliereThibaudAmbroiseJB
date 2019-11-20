package com.fr.adaming.web.dto;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BienDto {

	
	private long id;
	
	@Positive
	private double prix;
	
	private boolean vendu;
	
	private boolean deleted;


	public BienDto( double prix,  boolean vendu, boolean deleted) {
		super();
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}


	public BienDto(double prix, boolean deleted) {
		super();
		this.prix = prix;
		this.deleted = deleted;
	}


	public BienDto(long id, double prix, boolean vendu,boolean deleted) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
		this.deleted = deleted;
	}

	public BienDto(long id, double prix, boolean vendu) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
	}


	public BienDto( boolean vendu, boolean deleted) {
		super();
		this.vendu = vendu;
		this.deleted = deleted;
	}
	
}
