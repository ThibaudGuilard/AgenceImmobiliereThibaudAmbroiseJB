package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Bien;

public interface IBienController {

	public String saveBien (Bien bien);
	
	public List<Bien> findAll();
	
	public String updateBien(Bien bien);
	
	public Bien deleteBien(Bien bien);
	
}
