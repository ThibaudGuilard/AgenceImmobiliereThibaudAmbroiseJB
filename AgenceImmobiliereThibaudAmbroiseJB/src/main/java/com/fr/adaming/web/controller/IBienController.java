package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

public interface IBienController {

	public Bien createBien (@RequestBody @Valid BienDto dto);
	
	public List<Bien> findAll();
	
	public Bien updateBien(@RequestBody @Valid BienDto dto);
	
	public Bien deleteBien(long id);
	
}
