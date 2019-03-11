package com.teste.voiture.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.teste.voiture.Entity.Voiture;

public interface voitureRepository extends JpaRepository<Voiture, Long> {
	
	@Query("select v from voiture v where v.nom like :x")
	public Page<Voiture> chercherVoiture(@Param("x")String voitureNom,Pageable pageable);

	

}
