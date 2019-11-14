package com.fr.adaming.web.dto.converters;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDto;

public class AgentConverter {

	public Agent convertToClass(AgentDto dto) {
		Agent agent = new Agent();
		agent.setId(dto.getId());
		agent.setEmail(dto.getEmail());
		agent.setFullName(dto.getFullName());
		agent.setTelephone(dto.getTelephone());
		agent.setDeleted(dto.isDeleted());
		agent.setPwd(dto.getPwd());
		agent.setDateRecrutement(dto.getDateRecrutement());
		agent.setClients(dto.getClients());
		return agent;
	}

}
