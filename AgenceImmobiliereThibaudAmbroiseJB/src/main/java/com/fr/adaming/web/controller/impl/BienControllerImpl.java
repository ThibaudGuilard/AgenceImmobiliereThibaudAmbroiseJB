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
import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;
import com.fr.adaming.web.dto.BienConverter;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.ClientConverter;

@RestController
@RequestMapping(path = "api/bien")
public class BienControllerImpl implements IBienController {
	@Autowired
	private IBienService service;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String createBien(@RequestBody BienDto dto) {
		Bien bien = BienConverter.convertToClass(dto);
		Bien bienRetour = service.saveBien(bien);
		if (bienRetour != null) {
		service.saveBien(bien);
		return "client created";
		} else {
			return "client already exist";
		}
	}

	@GetMapping(path = "/{id}/searchbyid")
	public Bien searchParId(@PathVariable(name = "id") Long id) {
		return service.FindParId(id);
	}

	@GetMapping(path = "/print")
	public List<Bien> findAll() {
		return service.findAll();
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateBien(@RequestBody BienDto dto) {
		Bien bien = BienConverter.convertToClass(dto);
		boolean clientRetour = service.updateBien(bien);
		if (clientRetour == true) {
		service.updateBien(bien);
		return "Client updated";
		}else {
			return "This Client does'nt exist";
		}
	}

	@PostMapping(path="/delete")
	public boolean deleteBien(@RequestBody Bien bien) {
		return service.deleteBien(bien);
	}
	
}
