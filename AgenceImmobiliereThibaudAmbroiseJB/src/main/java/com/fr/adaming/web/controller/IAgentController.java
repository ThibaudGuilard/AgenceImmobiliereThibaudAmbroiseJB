package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDto;

public interface IAgentController {
	
	public String createAgent(AgentDto dto);
	
	public String deleteAgent(AgentDto dto);
	
	public String updateAgent(AgentDto dto);
	
	public List<Agent> printAgents();
		
}
