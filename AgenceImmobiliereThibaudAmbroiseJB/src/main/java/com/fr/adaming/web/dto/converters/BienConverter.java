package com.fr.adaming.web.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.BienDto;

public class BienConverter {

	public static Bien convertToClass(BienDto dto) {

		long id = dto.getId();
		double prix = dto.getPrix();
		boolean vendu = dto.isVendu();
		boolean deleted = dto.isDeleted();
		Client clients = dto.getClients();
		Bien bien = new Bien(id, prix, vendu, deleted, clients);
		return bien;
	}

	public static BienDto convertToDto(Bien bien) {
		long id = bien.getId();
		double prix = bien.getPrix();
		boolean vendu = bien.isVendu();
		boolean deleted = bien.isDeleted();
		Client clients = bien.getClients();
		BienDto dto = new BienDto(id, prix, vendu, deleted, clients);
		return dto;
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
