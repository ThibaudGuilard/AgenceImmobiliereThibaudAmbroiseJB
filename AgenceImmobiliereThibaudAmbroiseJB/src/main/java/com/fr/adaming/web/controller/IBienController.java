package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(path = "api/bien")
@Api(value="Bien Management System")
public interface IBienController {

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "Add a Bien")
	public Bien createBien (@RequestBody @Valid BienDto dto);
	
	@GetMapping(path = "/{id}/searchbyid")
	@ApiOperation(value = "Get a bien by Id")
	public Bien searchParId(@PathVariable(name = "id") long id);
	
	@GetMapping(path = "/print")
	@ApiOperation(value = "View a list of available Bien", response = List.class)
	public List<Bien> findAll();
	
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "Update a Bien")
	public Bien updateBien(@RequestBody @Valid BienDto dto);
	
	@PostMapping(path="{id}/delete")
	@ApiOperation(value = "Update the status of the attribut deleted with the id")
	public Bien deleteBien(@PathVariable(name ="id")long id);
	
}
