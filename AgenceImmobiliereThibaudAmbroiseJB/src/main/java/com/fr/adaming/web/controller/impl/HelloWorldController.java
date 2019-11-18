package com.fr.adaming.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.converters.ClientConverter;

@RestController
@RequestMapping(path = "api/hello")
public class HelloWorldController {
	
	@Autowired
	private ClientRepository dao;
	
	@GetMapping
	public String sayHello() {
		System.out.println("DEBUG : HelloWorld From Spring WEB");
		return "HelloWorld From Spring WEB";
	}
	
	public ClientDto create(@RequestBody ClientDto dto) {
		return ClientConverter.convertToDto(
					dao.save(ClientConverter.convertToClass(dto))
					);
	}

}
