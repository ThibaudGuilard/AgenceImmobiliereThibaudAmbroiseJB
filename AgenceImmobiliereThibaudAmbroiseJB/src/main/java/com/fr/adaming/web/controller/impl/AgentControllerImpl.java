package com.fr.adaming.web.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.web.controller.IAgentController;
import com.fr.adaming.web.dto.AgentDto;
import com.fr.adaming.web.dto.converters.AgentConverter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Jean-Baptiste
 *
 */
@RestController
@RequestMapping(path = "api/agent")
@CrossOrigin
@Api(description = "API pour les opérations sur les agents")
public class AgentControllerImpl implements IAgentController {

	@Autowired
	private IAgentService service;
	
	@Override
	@PostMapping(path = "/create_agent")
	@ApiOperation(value = "Crée un agent (même si l'adresse email")
	public AgentDto createAgent(@Valid @RequestBody AgentDto dto) {
		Agent agent = AgentConverter.convertToClass(dto);
		Agent savedAgent = service.save(agent);
		if(savedAgent != null) {
			AgentDto savedDto = AgentConverter.convertToDto1(savedAgent);
			return savedDto;	
		}else {
			return null;
		}
			
	}
	
	@Override
	@PostMapping(path = "/delete_agent")
	public AgentDto deleteAgent(@RequestBody AgentDto dto) {
		Agent agent = AgentConverter.convertToClass(dto) ;
		Agent deletedAgent = service.deleteAgent(agent);
		if (deletedAgent != null) {
			AgentDto deletedDto = AgentConverter.convertToDto(deletedAgent);
			return deletedDto;
		} else {
			return null;
		}
		
	}
	
	@Override
	@PostMapping(path = "/update_agent")
	public AgentDto updateAgent(@Valid @RequestBody AgentDto dto) {
		Agent agent = AgentConverter.convertToClass(dto);
		Agent updatedAgent = service.updateAgent(agent);
		if(updatedAgent != null) {
			AgentDto updatedDto = AgentConverter.convertToDto(updatedAgent);
			return updatedDto;	
		}else {
			return null;
		}
		
	}
	
	@Override
	@GetMapping(path = "/print_agents")
	public List<Agent> printAgents() {
		return service.findAll();
	}
	
}
