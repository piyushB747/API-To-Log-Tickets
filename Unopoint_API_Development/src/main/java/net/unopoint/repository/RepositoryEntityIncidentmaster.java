package net.unopoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.unopoint.entity.EntityIncidentmaster;


public interface RepositoryEntityIncidentmaster extends JpaRepository<EntityIncidentmaster, Long>{

	@Query(value="Select count(*) from incidentmaster where customer_cm_im =?1 and assetid_im =?2 "
			+ " and title_im =?3 and category_catm_im =?4  and deleteflag_im='N' and status_sm_im in(1,3,7) ",
			nativeQuery=true)
	public abstract int getTicketCount(String m_strCustId,String panelId,String title,String categoryId);
	
	/** To Find TicketStatus **/
	@Query(value="SELECT * from incidentmaster where typeid_im=?1 ",nativeQuery=true)
	public abstract List<EntityIncidentmaster> returnTicketStatus(Long ticketId);
	
	@Query("SELECT ei FROM EntityIncidentmaster ei where ei.deleteFlagIm='N' and ei.statusSmIm IN(1,3,7,6,5,4) and ei.modeIm='Mobile' order"
			+ " by ei.typeIdIm desc ")
	public abstract List<EntityIncidentmaster> returnListOfTicketCreatedByMobile();

	
	//** ReturningList Of Open Count**//
	@Query(value="Select count(*) from incidentmaster where  deleteflag_im='N' and status_sm_im IN(1) ",
			nativeQuery=true)
	public abstract int getCountOfOpenTicket();
	
	/**Return List Of Ticket CustomerWise 18-04-23**/
	@Query("SELECT ei FROM EntityIncidentmaster ei where ei.deleteFlagIm='N' and ei.statusSmIm IN(1,3,7,6,5,4) and ei.modeIm='Mobile' and "
			+ " ei.customerCmIM=?1  order"
			+ " by ei.typeIdIm desc ")
	public abstract List<EntityIncidentmaster> returnListOfTicketCreatedByMobileCustomerWise(int n_intCustId);

	/***Return Total Counts Of Open TicketCustomerWise**/
	@Query(value="Select count(*) from incidentmaster where  deleteflag_im='N' and status_sm_im IN(1) and customer_cm_im=?1 ",
			nativeQuery=true)
	public abstract int getCountOfOpenTicketCustomerWise(int n_intCustId);
	
	/***Return Total Counts Of Total TicketCustomerWise**/
	@Query(value="Select count(*) from incidentmaster where  deleteflag_im='N'  and customer_cm_im=?1 ",
			nativeQuery=true)
	public abstract int getCountoFTotalTicketCustomerWise(int n_intCustId);
	
	
	
}
