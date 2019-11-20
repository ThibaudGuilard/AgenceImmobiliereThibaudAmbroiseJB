package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDto;

@CrossOrigin
public interface IAgentController {
	
	public AgentDto createAgent(AgentDto dto);
	
	public AgentDto deleteAgent(AgentDto dto);
	
	public AgentDto updateAgent(AgentDto dto);
	
	public List<Agent> printAgents();
		
}
