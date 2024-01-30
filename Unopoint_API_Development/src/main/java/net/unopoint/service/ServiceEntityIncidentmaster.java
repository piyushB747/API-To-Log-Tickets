package net.unopoint.service;

import java.util.List;

import net.unopoint.bean.BeanJsonResponse;
import net.unopoint.bean.BeanJsonTicketResponse;
import net.unopoint.dto.DtoIncidentMaster;

public interface ServiceEntityIncidentmaster {

	public abstract BeanJsonResponse createIncident(String m_strCustId,String panelId,String title,String m_strCatId,String m_strIncType,String m_strBranchCat,String description);
	
	public abstract BeanJsonTicketResponse findTicketStatus(Long id);
	
	//** Return List Of All Tickets **//
	public abstract List<DtoIncidentMaster> returnListOfAllTicketWhichAreLoggedThroughMobile();
	
    public abstract List<DtoIncidentMaster> returnListOfAllTicketCustomerWiseLogged(String m_strCustomerName);	
}
