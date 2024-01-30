package net.unopoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.unopoint.entity.EntityCategorymst;

public interface RepositoryEntityCategorymst extends JpaRepository<EntityCategorymst, Long>{
	
	public abstract EntityCategorymst findByTypeValueCategoryAndDeleteFlagCategory(String typeValue,String deleteFlag);
	
	
	@Query(value="Select typevalue_cm from categorymst_cm where deleteflag_cm='N' and typeid_cm =?1 ",nativeQuery=true)
	public abstract String returnFindByTypeIdBcmAndDeleteFlagBcmCategory(Long typeidCm);
	
	
}
