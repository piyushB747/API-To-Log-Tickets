package net.unopoint.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.unopoint.entity.EntityAPICreateTicketDetails;
import net.unopoint.repository.RepositoryEntityAPICreateTicketDetails;

@Service
public class ServiceImplEntityAPICreateTicketDetails {

	@Autowired
	private RepositoryEntityAPICreateTicketDetails repositoryEntityAPICreateTicketDetails;
	
	public void insertIntoApiLog(String incidentId,String m_strCustID,String panelId,String title,String m_strCatId,String m_strIncType,String m_strBranchCat,String description)
	{
  	    EntityAPICreateTicketDetails entityAPICreateTicketDetails=new EntityAPICreateTicketDetails();
		 entityAPICreateTicketDetails.setIncidentIdActd(incidentId);
		 entityAPICreateTicketDetails.setCustomerCmActd(m_strCustID);
		 entityAPICreateTicketDetails.setPanelIdActd(panelId);
		 entityAPICreateTicketDetails.setTitleActd(title);
		 entityAPICreateTicketDetails.setCategoryActd(Integer.parseInt(m_strCatId));
	   	 entityAPICreateTicketDetails.setIncTypeActd(Integer.parseInt(m_strIncType));
		 entityAPICreateTicketDetails.setBranchCategoryActd(Integer.parseInt(m_strBranchCat));
		 entityAPICreateTicketDetails.setDescriptionActd(description);	
		repositoryEntityAPICreateTicketDetails.save(entityAPICreateTicketDetails);
		
	}	
}
