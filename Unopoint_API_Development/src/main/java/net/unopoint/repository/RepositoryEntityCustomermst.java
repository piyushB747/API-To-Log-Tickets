package net.unopoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.unopoint.entity.EntityCustomermst;

public interface RepositoryEntityCustomermst extends JpaRepository<EntityCustomermst, Long>{

	public abstract EntityCustomermst findByCustomerNameAndDeleteFlag(String customerName,String deleteflag);
	
	/**
	@Query("SELECT em.fnameEm,em.lnameEm FROM EntityEngineerMaster em where em.deleteFlagEm =?2 AND"
			+ " em.resignedFlagEm='N' and  em.typeidEm =?1 ")
	public EntityEngineerMaster returnFnamForId();
	**/
	
	@Query("SELECT em.customerName FROM EntityCustomermst em where em.deleteFlag ='N' AND"
			+ "  em.typeId =?1 ")
	public abstract String findCustomerNameById(Long customerName);

}

