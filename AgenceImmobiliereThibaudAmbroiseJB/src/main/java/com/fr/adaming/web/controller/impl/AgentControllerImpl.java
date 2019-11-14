package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.web.controller.IAgentController;
import com.fr.adaming.web.dto.AgentDto;
import com.fr.adaming.web.dto.converters.AgentConverter;

/**
 * @author Jean-Baptiste
 *
 */
@RestController
public class AgentControllerImpl implements IAgentController {

	@Autowired
	private IAgentService service;
	
	@Override
	@PostMapping(path = "/create_agent")
	public String createAgent(@RequestBody AgentDto dto) {
		Agent agent = AgentConverter.convertToClass(dto);
		if(service.save(agent) == null) {
			return "Agent already exists";
		} else {
			return "Agent created";
		}
		
	}
	
	@Override
	@PostMapping(path = "/delete_agent")
	public String deleteAgent(@RequestBody AgentDto dto) {
		Agent agent = AgentConverter.convertToClass(dto) ;
		if (service.deleteAgent(agent) == null) {
			return "Agent does not exist";
		} else {
			return "Agent deleted";
		}
		
	}
	
	@Override
	@PostMapping(path = "/update_agent")
	public String updateAgent(@RequestBody AgentDto dto) {
		Agent agent = AgentConverter.convertToClass(dto);
		if(service.updateAgent(agent)) {
			return "Agent updated";	
		}else {
			return "Agent does not exist";
		}
		
	}
	
	@Override
	@GetMapping(path = "/print-agents")
	public List<Agent> printAgents() {
		return service.findAll();
	}
	
}
