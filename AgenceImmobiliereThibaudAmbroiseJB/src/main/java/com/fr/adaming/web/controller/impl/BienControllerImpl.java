// Ambroise RENE

package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;

@RestController
@RequestMapping(path = "api/bien")
public class BienControllerImpl implements IBienController {
	@Autowired
	private IBienService service;

	@RequestMapping(path = "/ajouter", method = RequestMethod.POST)
	public String saveBien(@RequestBody Bien bien) {
		if (service.saveBien(bien) != null) {
			return "Ajouter SUCCES";
		} else {
			return "Ajouter FAIL";
		}
	}

	@GetMapping(path = "/{id}/chercherid")
	public Bien searchParId(@PathVariable(name = "id") Long id) {
		return service.FindParId(id);
	}

	@GetMapping(path = "/afficher")
	public List<Bien> findAll() {
		return service.findAll();
	}

	@RequestMapping(path = "/modifier", method = RequestMethod.POST)
	public String updateBien(@RequestBody Bien bien) {
		if (service.updateBien(bien)) {
			return "Modifier SUCCES";
		} else {
			return "Modifier FAIL";
		}
	}

	@PostMapping(path="/supprimer")
	public boolean deleteBien(@RequestBody Bien bien) {
		return service.deleteBien(bien);
	}
	
}
