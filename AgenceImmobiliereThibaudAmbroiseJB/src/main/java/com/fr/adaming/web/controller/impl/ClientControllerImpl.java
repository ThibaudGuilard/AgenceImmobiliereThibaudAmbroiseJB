package com.fr.adaming.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;

/**
 * @author Thibaud
 *
 */
@RestController
public class ClientControllerImpl implements IClientController {

	@Autowired
	private IClientService service;
	
}
