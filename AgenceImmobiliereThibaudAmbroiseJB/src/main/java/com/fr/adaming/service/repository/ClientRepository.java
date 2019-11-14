package com.fr.adaming.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
