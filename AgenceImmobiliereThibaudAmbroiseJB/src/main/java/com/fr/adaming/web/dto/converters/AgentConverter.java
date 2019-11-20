package com.fr.adaming.web.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDto;

// Ajouter if else pour les objets null
public class AgentConverter {

	public static Agent convertToClass(AgentDto dto) {
		if (dto == null) {
			return null;
		}
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
	
	public static AgentDto convertToDto(Agent agent) {
		if (agent == null) {
			return null;
		}
		AgentDto dto = new AgentDto();
		dto.setId(agent.getId());
		dto.setEmail(agent.getEmail());
		dto.setFullName(agent.getFullName());
		dto.setTelephone(agent.getTelephone());
		dto.setDeleted(agent.isDeleted());
		dto.setPwd(agent.getPwd());
		dto.setDateRecrutement(agent.getDateRecrutement());
		dto.setClients(agent.getClients());
		return dto;
	}
	
	public static Agent convertToClass1(AgentDto dto) {
		if (dto == null) {
			return null;
		}
		Agent agent = new Agent();
		agent.setEmail(dto.getEmail());
		agent.setFullName(dto.getFullName());
		agent.setTelephone(dto.getTelephone());
		agent.setDeleted(dto.isDeleted());
		agent.setPwd(dto.getPwd());
		agent.setDateRecrutement(dto.getDateRecrutement());
		return agent;
	}
	
	public static AgentDto convertToDto1(Agent agent) {
		if (agent == null) {
			return null;
		}
		AgentDto dto = new AgentDto();
		dto.setEmail(agent.getEmail());
		dto.setFullName(agent.getFullName());
		dto.setTelephone(agent.getTelephone());
		dto.setDeleted(agent.isDeleted());
		dto.setPwd(agent.getPwd());
		dto.setDateRecrutement(agent.getDateRecrutement());
		return dto;
	}
	
	public static List<Agent> convertListDtoToListAgent(List<AgentDto> dtos){
		if (dtos == null) {
			return null;
		}
		List<Agent> agents = new ArrayList<Agent>();
		for(AgentDto d : dtos) {
			agents.add(AgentConverter.convertToClass(d));
		}
		return agents;
	}
	
	public static List<AgentDto> convert(List<Agent> agents){
		if (agents == null) {
			return null;
		}
		List<AgentDto> dtos = new ArrayList<AgentDto>();
		for (Agent a : agents) {
			dtos.add(AgentConverter.convertToDto(a));
		}
		return dtos;
	}

}
