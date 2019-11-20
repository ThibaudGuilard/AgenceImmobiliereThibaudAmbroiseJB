// Ambroise RENE

package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.repository.BienRepository;
import com.fr.adaming.service.IBienService;

@Service
public class BienServiceImpl implements IBienService{

	
	@Autowired
	private BienRepository repository;
	

	public Bien saveBien(Bien bien) {

		if (repository.existsById(bien.getId())==false) {
			return repository.save(bien);
		}else {
			return null;
		}
		
	}


	public List<Bien> findAll(){
		return repository.findAll();
	}
	
	public Bien updateBien(Bien bien) {
		
		// Chercher bien par id
		if(repository.existsById(bien.getId())) {	
			return repository.save(bien);
		}else {
			return null;
		}
	}
	

	public Bien deleteBien(long id) {
		// Changer la valeur de l'attribut "deleted"
		if (repository.existsById(id) == true ) {
			Bien bien = repository.findById(id).get();
			bien.setDeleted(true);
			 return repository.save(bien);
		} else {
			return null;
		}
	}

	public Bien FindById(Long id) {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}


}
