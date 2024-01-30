package net.unopoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unopoint.entity.EntityStatusmstsm;

public interface RepositoryEntityStatusmstsm extends JpaRepository<EntityStatusmstsm, Long>{

	public abstract EntityStatusmstsm findByTypeidSm(Long typeid_sm);
	
}
