package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

@CrossOrigin
@RequestMapping(path = "api/bien")
public interface IBienController {

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public Bien createBien (@RequestBody @Valid BienDto dto);
	
	@GetMapping(path = "/{id}/searchbyid")
	public Bien searchParId(@PathVariable(name = "id") long id);
	
	@GetMapping(path = "/print")
	public List<Bien> findAll();
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public Bien updateBien(@RequestBody @Valid BienDto dto);
	
	@PostMapping(path="{id}/delete")
	public Bien deleteBien(@PathVariable(name ="id")long id);
	
}
