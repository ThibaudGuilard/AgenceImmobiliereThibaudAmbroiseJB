package com.fr.adaming.web.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

public class BienConverter {

	public static Bien convertToClass(BienDto dto) {
		if(dto == null){
			return null;
		}else {
		long id = dto.getId();
		double prix = dto.getPrix();
		boolean vendu = dto.isVendu();
		boolean deleted = dto.isDeleted();
		Bien bien = new Bien(id, prix, vendu, deleted);
		return bien;
		}
	}

	public static BienDto convertToDto(Bien bien) {
		if(bien == null){
			return null;
		}else {
		long id = bien.getId();
		double prix = bien.getPrix();
		boolean vendu = bien.isVendu();
		boolean deleted = bien.isDeleted();
		BienDto dto = new BienDto(id, prix, vendu, deleted);
		return dto;
		}
	}

	public static List<Bien> convertListToClass(List<BienDto> dtos) {
		List<Bien> clients = new ArrayList<Bien>();
		for (BienDto dto : dtos) {
			Bien bien = convertToClass(dto);
			clients.add(bien);
		}
		return clients;
	}

	public static List<BienDto> convertListToDto(List<Bien> biens) {
		List<BienDto> dtos = new ArrayList<BienDto>();
		for (Bien bien : biens) {
			BienDto dto = convertToDto(bien);
			dtos.add(dto);
		}
		return dtos;
	}

}
