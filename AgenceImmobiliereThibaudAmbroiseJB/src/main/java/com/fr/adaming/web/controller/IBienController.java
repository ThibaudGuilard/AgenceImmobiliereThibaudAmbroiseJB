package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

public interface IBienController {

	public String createBien (BienDto dto);
	
	public List<Bien> findAll();
	
	public String updateBien(BienDto dto);
	
	public Bien deleteBien(Bien bien);
	
}
