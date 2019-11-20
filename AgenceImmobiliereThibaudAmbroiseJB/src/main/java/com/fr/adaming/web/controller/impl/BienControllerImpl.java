// Ambroise RENE

package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.converters.BienConverter;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@RequestMapping(path = "api/bien")
@Api(description = "API pour les opérations sur les biens")
public class BienControllerImpl implements IBienController {
	@Autowired
	private IBienService service;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Bien createBien(BienDto dto) {
		Bien bien = BienConverter.convertToClass(dto);
			return service.saveBien(bien);
		
	}
	
	@GetMapping(path = "/{id}/searchbyid")
	public Bien searchParId(@PathVariable(name = "id") long id) {
		return service.FindById(id);
	}

	@GetMapping(path = "/print")
	public List<Bien> findAll() {
		return service.findAll();
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public Bien updateBien(BienDto dto) {
		Bien bien = BienConverter.convertToClass(dto);
		return service.updateBien(bien);

	}

	@PostMapping(path="{id}/delete")
	public Bien deleteBien(@PathVariable(name ="id") long id) {
		return service.deleteBien(id);	
	}
	
}
