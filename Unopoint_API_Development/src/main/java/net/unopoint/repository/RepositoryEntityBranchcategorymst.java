package net.unopoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.unopoint.entity.EntityBranchcategorymst;


public interface RepositoryEntityBranchcategorymst extends JpaRepository<EntityBranchcategorymst, Long>{

	
	public abstract EntityBranchcategorymst findByTypeValueBcmAndDeleteFlagBcm(String typeValue,String deleteFlag);
	
	
	@Query(value="Select typevalue_bcm from branchcategorymst_bcm where deleteflag_bcm='N' and typeid_bcm =?1 ",nativeQuery=true)
	public abstract String returnFindByTypeIdBcmAndDeleteFlagBcm(Long typeidBcm);
	
	
}
