package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Thibaud
 *
 */
@MappedSuperclass
@Setter @Getter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String fullName;
	
//	@Pattern(regexp = "\\d{10}")
	private int telephone;
	
	private boolean deleted;
	
	public User(String email, String fullName, int telephone,boolean deleted) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
		this.deleted = deleted;
	}
	
	

}
