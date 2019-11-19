// Ambroise RENE

package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.repository.BienRepository;
import com.fr.adaming.service.IBienService;

@Service
public class BienServiceImpl implements IBienService{

	
	@Autowired
	private BienRepository repository;
	

	/*
	 * (non-Javadoc)
	 * @see com.fr.adaming.entity.service.IBienService#save()
	 */
	
	public Bien saveBien(Bien bien) {
		
		// Vérifier si le bien existe dans la BD (email)
//		Bien c = dao.findByEmail(bien.getEmail());
		
		Bien b = new Bien();
		b.setId(bien.getId());
		
		
		if(repository.exists(Example.of(b))) {
			//Le bien existe dans la BD (FAIL)
			return null;
		}else {
			//Le bien n'existe pas (SUCCESS) : enregistrer le bien dans la BD et retourner le bien
			return repository.save(bien);
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
	

	public Bien deleteBien(Bien bien) {
		// Changer la valeur de l'attribut "deleted"
		if (FindParId(bien.getId()) != null) {
			 repository.supprimerBien(bien.getId());
			 return bien;
		} else {
			return null;
		}
	}

	public Bien FindParId(Long id) {
		try {
			return repository.findId(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

}
