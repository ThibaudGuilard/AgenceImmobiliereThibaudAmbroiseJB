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

	@PostMapping(path="/supprimer")
	public Bien supprimer(@RequestBody Bien bien) {
		return service.deleteBien(bien);
	}

	@GetMapping(path = "/{id}/chercherid")
	public Bien chercherParId(@PathVariable(name = "id") Integer id) {
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

	@Override
	public Bien deleteBien(Bien bien) {
		// TODO Auto-generated method stub
		return null;
	}

}
