package net.unopoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import net.unopoint.entity.EntityIncidenttypemst;


public interface RepositoryEntityIncidenttypemst extends JpaRepository<EntityIncidenttypemst, Long>{
     
	public abstract EntityIncidenttypemst  findBytypeValueAndDeleteFlag(String typeValue,String deleteFlag);
	
	@Query(value="select typevalue_itm from incidenttypemst_itm  where typeid_itm =?1 and deleteflag_itm='N' ",nativeQuery = true)
	public abstract String returnStringValueOfIncidentType(Long typeid);
	 
}
