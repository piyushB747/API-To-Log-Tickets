package net.unopoint.serviceimpl;

import java.util.Optional;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import net.unopoint.bean.BeanCustomerasset_cad;
import net.unopoint.bean.BeanJsonResponse;
import net.unopoint.bean.BeanJsonTicketResponse;
import net.unopoint.dto.DtoIncidentMaster;
import net.unopoint.entity.EntityIncidentmaster;
import net.unopoint.entity.EntityStatusmstsm;
import net.unopoint.repository.RepositoryEntityBranchcategorymst;
import net.unopoint.repository.RepositoryEntityCategorymst;
import net.unopoint.repository.RepositoryEntityCustomermst;
import net.unopoint.repository.RepositoryEntityIncidentmaster;
import net.unopoint.repository.RepositoryEntityIncidenttypemst;
import net.unopoint.repository.RepositoryEntityStatusmstsm;
import net.unopoint.service.ServiceEntityIncidentmaster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceImplEntityIncidentmaster implements ServiceEntityIncidentmaster{
	
	
	@Autowired(required = true)
	private ModelMapper modelMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RepositoryEntityIncidentmaster repositoryEntityIncidentmaster;

	@Autowired
	private RepositoryEntityCategorymst repoEntityCategory;
	
	@Autowired
	private RepositoryEntityStatusmstsm entityRepoStatusmstsm;
	
	@Autowired
	private RepositoryEntityBranchcategorymst repoEntityBranchCat;
	
	@Autowired
	private RepositoryEntityIncidenttypemst repoEntityIncidentType;
	
	@Autowired
	private RepositoryEntityCustomermst repoEntityCustomer;   //Added at 18April
	
	
	@Override
	public BeanJsonResponse createIncident(String m_strCustId, String panelId, String title, String m_strCatId, String m_strIncType,
			String m_strBranchCat, String description) {
		
		
		/*ADDED BY PIYUSH TO RETURN TIME DATE 1 AUG 2023*/
		LocalDate obj_CurrentDate = LocalDate.now();
		/**
        int n_intCurrentYear = obj_CurrentDate.getYear();                                           //current Year
        Month obj_currentMonth = obj_CurrentDate.getMonth();                                       //current Month  FEBURARY
        String m_strCurrentMonthName=String.valueOf(obj_currentMonth);
        int n_intCurrentMonthNumber = obj_CurrentDate.getMonthValue();                              //current Month 2
        String m_strCurrentDay = obj_CurrentDate.format(DateTimeFormatter.ofPattern("EEEE"));       //Current Day SUNDAY
        int n_intcurrentDayDateNumber = obj_CurrentDate.getDayOfMonth();                            //Current Day Date 26
        **/
            
        LocalTime currentTime = LocalTime.now();                                                   //Current Time	
        
        
		
		BeanJsonResponse beanJsonResponse=new BeanJsonResponse();
		
		EntityIncidentmaster incidentmaster=new EntityIncidentmaster();
		incidentmaster.setCustomerCmIM(Integer.parseInt(m_strCustId));
		incidentmaster.setAssetIdIm(panelId);
		incidentmaster.setTitleIm(title);
		incidentmaster.setCategoryCatIm(Integer.parseInt(m_strCatId));
		incidentmaster.setIncTypeItmIm(String.valueOf(m_strIncType));     //This was integer Now I am changing String
		incidentmaster.setBranchCategoryIm(Integer.parseInt(m_strBranchCat));
		incidentmaster.setDescriptionIm(description);
		incidentmaster.setDeleteFlagIm("N");
		incidentmaster.setStatusSmIm(7);
		incidentmaster.setModeIm("Mobile");
		
		/*ADDED ON 1AUG 2023 By PIYUSH*/
		incidentmaster.setIncdateIm(""+obj_CurrentDate);    
		incidentmaster.setInctimeIm(""+currentTime);
		/*ADDED ON 1AUG 2023 By PIYUSH*/
		
		try {
				EntityIncidentmaster entityIncidentmaster=repositoryEntityIncidentmaster.save(incidentmaster);
				System.out.println("OUTPUT:- "+entityIncidentmaster.getTypeIdIm());
				
				 
				
				beanJsonResponse.setIncidentId(String.valueOf(entityIncidentmaster.getTypeIdIm()));
				beanJsonResponse.setResponseStatus("TicketCreated");
				beanJsonResponse.setCreatedDateTime(""+obj_CurrentDate+" "+currentTime);
				
		}catch (Exception e) { e.printStackTrace();  }
	
		return beanJsonResponse;
		
	}

	
	public EntityIncidentmaster findTicketById(Long id) {

		Optional<EntityIncidentmaster> optional = repositoryEntityIncidentmaster.findById(id);
		EntityIncidentmaster employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" TicketNot Not Found:: " + id);
		}
		return employee;
		
	}
	
	@Override
	public BeanJsonTicketResponse findTicketStatus(Long id) {
		
		BeanJsonTicketResponse beanJsonTicketResponse=new BeanJsonTicketResponse();
		EntityIncidentmaster entityIncidentMaster=this.findTicketById(id);
		
		String m_strTicketStatusId=String.valueOf(entityIncidentMaster.getStatusSmIm());
		
		
		//Converting Status integer ID Into String Status 
		EntityStatusmstsm entityStatusmstsm=entityRepoStatusmstsm.findByTypeidSm(Long.parseUnsignedLong(m_strTicketStatusId));
		beanJsonTicketResponse.setTicketId(String.valueOf(entityIncidentMaster.getTypeIdIm()));
        if(entityStatusmstsm.getTypeValueSm().equals("Open")  || entityStatusmstsm.getTypeValueSm().equals("PFV") || entityStatusmstsm.getTypeValueSm().equals("Paused")) {
			
        	
        	if(entityStatusmstsm.getTypeValueSm().equals("PFV")) {
        		  beanJsonTicketResponse.setTicketStatus("Open");
        	}else {
        		  beanJsonTicketResponse.setTicketStatus(entityStatusmstsm.getTypeValueSm());
        	}
        	
        	beanJsonTicketResponse.setDescription(entityIncidentMaster.getDescriptionIm());
			beanJsonTicketResponse.setTitle(entityIncidentMaster.getTitleIm());
		}else if(entityStatusmstsm.getTypeValueSm().equals("Closed")) {
			
			
			beanJsonTicketResponse.setTicketStatus(entityStatusmstsm.getTypeValueSm());
			beanJsonTicketResponse.setDescription(entityIncidentMaster.getDescriptionIm());
			beanJsonTicketResponse.setTitle(entityIncidentMaster.getTitleIm());
			beanJsonTicketResponse.setClosedDate(entityIncidentMaster.getClosedDateIm());
			beanJsonTicketResponse.setClosedTime(entityIncidentMaster.getClosedtime_im());
			beanJsonTicketResponse.setResolution(entityIncidentMaster.getResolutionIm());
			beanJsonTicketResponse.setResolutionDescription(entityIncidentMaster.getResolutionDescriptionIm());
			beanJsonTicketResponse.setCustomerVist(entityIncidentMaster.getCustomerVisitIm());
			entityIncidentMaster.getDescriptionIm();
			entityIncidentMaster.getCustomerVisitIm();
			
			entityIncidentMaster.getResolutionDescriptionIm();
			entityIncidentMaster.getClosedtime_im();
			entityIncidentMaster.getModeIm();
			entityIncidentMaster.getTitleIm();
			entityIncidentMaster.getAssetIdIm();
			entityIncidentMaster.getSensorRemarkIm();
			entityIncidentMaster.getResolutionIm();
			
		}else {
			
			beanJsonTicketResponse.setTicketStatus(entityStatusmstsm.getTypeValueSm());
			beanJsonTicketResponse.setDescription(entityIncidentMaster.getDescriptionIm());
			beanJsonTicketResponse.setTitle(entityIncidentMaster.getTitleIm());
		}
		
		return beanJsonTicketResponse;
		
	}

	/** PLEASE DONT REMVOE THIS CODE BECAUSE WE ARE NOT USING MAPPING HERE**/
	
	public String CustomerAddress="";
	public String custEngName="";
	public String custState="";
	public String custPoplocation="";
	public String custCity="";
	public String custRegion="";
	
    /**RETURN LIST OF TICKETS CUSTOMERWISE 18APRIL 2023 **/
	@Override
	public List<DtoIncidentMaster> returnListOfAllTicketCustomerWiseLogged(
			String m_strCustomerId) {
		
        List<DtoIncidentMaster>  m_ObjListOfIncidentMaster=new ArrayList<>();
        int n_intCustId=Integer.parseInt(m_strCustomerId);
		try {
		 m_ObjListOfIncidentMaster= repositoryEntityIncidentmaster.
				returnListOfTicketCreatedByMobileCustomerWise(n_intCustId)
				.stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
         try {
        	 
        	 m_ObjListOfIncidentMaster.forEach((p)->{
        		 
               	 if(p.getStatusSmIm().equals("1") || p.getStatusSmIm().equals("7")) {
               		 p.setStatusSmIm("Open");
               	 }else if(p.getStatusSmIm().equals("3")) {
               		 p.setStatusSmIm("Paused");
               	 }else if(p.getStatusSmIm().equals("5")) {
               		 p.setStatusSmIm("Technician Closed");
               	 }else if(p.getStatusSmIm().equals("4")){
               		 p.setStatusSmIm("Closed");
               	 }else{
               		 p.setStatusSmIm("ReOpened");
               	 }
               	 
               	 int custId= p.getCustomerCmIM();
               	 String customermst=repoEntityCustomer.findCustomerNameById(Long.valueOf(n_intCustId));
               	 p.setCustomerName(customermst);
               	 
               	 String m_strAssetId=p.getAssetIdIm();
               	 
               	 @SuppressWarnings("unused")
               	 String x2=this.getCustomerDetails(custId,m_strAssetId);
                 p.setCustomerAddress(CustomerAddress);
                 p.setCustCity(custCity);
                 p.setCustRegion(custRegion);
                 p.setCustEngName(custEngName);
                 p.setCustState(custState);
                 p.setCustPoplocation(custPoplocation);
                 
                 //Finding BranchCategory value: converting id into value
                 String typeValueBcm=repoEntityBranchCat.returnFindByTypeIdBcmAndDeleteFlagBcm(Long.valueOf(p.getBranchCategoryIm()));       
                 p.setBranchCategoryIm(typeValueBcm);
                 
                 //Finding Category Value :converting id into value
                 String typeValueCat=repoEntityCategory.returnFindByTypeIdBcmAndDeleteFlagBcmCategory(Long.valueOf(p.getCategoryCatIm()));
                 p.setCategoryCatIm(typeValueCat);
                 
                 
                 
                 
                 //Finding incident-type value :converting id into value 
                 try {
                   String typeValueIncidentType=repoEntityIncidentType.returnStringValueOfIncidentType(Long.valueOf(p.getIncTypeItmIm()));
                   p.setIncTypeItmIm(typeValueIncidentType);
                 }catch(Exception ex) { }
        		
        	 });
        	 
         }catch(Exception ex) { ex.printStackTrace(); }
		
		}catch(Exception ex) { ex.printStackTrace();  }
		return m_ObjListOfIncidentMaster;
	}

	
	//Return List Of All Tickets which are Created Through Mobile API;
	@Override
	public List<DtoIncidentMaster> returnListOfAllTicketWhichAreLoggedThroughMobile() {
		List<DtoIncidentMaster>  m_ObjListOfIncidentMaster= repositoryEntityIncidentmaster.
				returnListOfTicketCreatedByMobile()
				.stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
		m_ObjListOfIncidentMaster.forEach((p)->{
       	 if(p.getStatusSmIm().equals("1") || p.getStatusSmIm().equals("7")) {
       		 p.setStatusSmIm("Open");
       	 }else if(p.getStatusSmIm().equals("3")) {
       		 p.setStatusSmIm("Paused");
       	 }else if(p.getStatusSmIm().equals("5")) {
       		 p.setStatusSmIm("Technician Closed");
       	 }else if(p.getStatusSmIm().equals("4")){
       		 p.setStatusSmIm("Closed");
       	 }else
       	 {
       		 p.setStatusSmIm("ReOpened");
       	 }
       	 
       	 int custId= p.getCustomerCmIM();
       	 String m_strAssetId=p.getAssetIdIm();
       	 
       	 @SuppressWarnings("unused")
       	 String x2=this.getCustomerDetails(custId,m_strAssetId);
         p.setCustomerAddress(CustomerAddress);
         p.setCustCity(custCity);
         p.setCustRegion(custRegion);
         p.setCustEngName(custEngName);
         p.setCustState(custState);
         p.setCustPoplocation(custPoplocation);
         
         //Finding BranchCategory value: converting id into value
         String typeValueBcm=repoEntityBranchCat.returnFindByTypeIdBcmAndDeleteFlagBcm(Long.valueOf(p.getBranchCategoryIm()));       
         p.setBranchCategoryIm(typeValueBcm);
         
         //Finding Category Value :converting id into value
         String typeValueCat=repoEntityCategory.returnFindByTypeIdBcmAndDeleteFlagBcmCategory(Long.valueOf(p.getCategoryCatIm()));
         p.setCategoryCatIm(typeValueCat);
         
         
         //Finding incident-type value :converting id into value 
         try {
           String typeValueIncidentType=repoEntityIncidentType.returnStringValueOfIncidentType(Long.valueOf(p.getIncTypeItmIm()));
           p.setIncTypeItmIm(typeValueIncidentType);
         }catch(Exception ex) { }
         
		});
		 
		return m_ObjListOfIncidentMaster;
	}

	
	
	
	
	
	/**PLEASE DON'T REMOVE THIS CODE HERE WE ARE USING JDBC TEMPLATE TO PERFOEM OPERATION**/
	public String getCustomerDetails(int m_strLongID,String assetId) {
		
	    String sqlSelect = "select City,POPLocation, State,CustomerAddress,AssetID,Engineername,region_cad from customerasset_cad"+m_strLongID+ " where assetId='"+assetId+"' ";
	    
	    @SuppressWarnings("unused")
         List<BeanCustomerasset_cad> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<BeanCustomerasset_cad>() {
        	 public BeanCustomerasset_cad mapRow(ResultSet result, int rowNum) throws SQLException {
	        	 BeanCustomerasset_cad contact = new BeanCustomerasset_cad();        	 
	        	
					CustomerAddress = result.getString("CustomerAddress");
					custEngName = result.getString("Engineername");
					custRegion = result.getString("region_cad");
					custState = result.getString("State");
					custPoplocation = result.getString("POPLocation");
					custCity=result.getString("City");
	        	 return contact;  
	        	 }
        	 });
                  
	    
         return null;
}
	
	
	
	
	/**Converting Entity Into DTO using Library**/
	public DtoIncidentMaster convertEntityToDto(EntityIncidentmaster user) {		
	    DtoIncidentMaster userLocationDto=new DtoIncidentMaster();
		userLocationDto=modelMapper.map(user, DtoIncidentMaster.class);
		return userLocationDto;		
	}


}
