// Ambroise RENE

package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.converters.BienConverter;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin
@Api(description = "API pour les opérations sur les biens")
public class BienControllerImpl implements IBienController {
	@Autowired
	private IBienService service;

	
	public Bien createBien(BienDto dto) {
		Bien bien = BienConverter.convertToClass(dto);
			return service.saveBien(bien);	
	}
	
	public Bien searchParId( long id) {
		return service.FindById(id);
	}

	public List<Bien> findAll() {
		return service.findAll();
	}

	public Bien updateBien(BienDto dto) {
		Bien bien = BienConverter.convertToClass(dto);
		return service.updateBien(bien);

	}

	public Bien deleteBien( long id) {
		return service.deleteBien(id);	
	}
	
}
