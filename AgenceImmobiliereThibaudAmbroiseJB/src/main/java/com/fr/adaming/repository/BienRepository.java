package com.fr.adaming.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fr.adaming.entity.Bien;

@Repository
public interface BienRepository extends JpaRepository<Bien,Long>{

	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "UPDATE Bien SET deleted = true where id like :id", nativeQuery = true)
	public void supprimer(@Param(value = "id") Integer id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "UPDATE Bien SET deleted = false where id like :id", nativeQuery = true)
	public void restaurer(@Param(value = "id") Integer id);
	
	
	@Query(value = "SELECT * FROM Bien WHERE deleted = false", nativeQuery = true)
	public List<Bien> listAll();
	
	
	@Query(value = "SELECT * FROM Bien WHERE id = :id AND deleted = false", nativeQuery = true)
	public Optional<Bien> findId(@Param(value = "id") Integer id);
	
//	public List<Bien> findById(Long id);
}
