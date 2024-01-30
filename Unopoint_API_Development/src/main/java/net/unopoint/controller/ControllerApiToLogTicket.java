package net.unopoint.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.unopoint.bean.BeanCustomerasset_cad;
import net.unopoint.bean.BeanJsonResponse;
import net.unopoint.entity.EntityBranchcategorymst;
import net.unopoint.entity.EntityCategorymst;
import net.unopoint.entity.EntityCustomermst;
import net.unopoint.entity.EntityIncidenttypemst;
import net.unopoint.repository.RepositoryEntityBranchcategorymst;
import net.unopoint.repository.RepositoryEntityCategorymst;
import net.unopoint.repository.RepositoryEntityIncidentmaster;
import net.unopoint.repository.RepositoryEntityIncidenttypemst;
//import net.unopoint.jdbcdao.RelationalDataAccess;
import net.unopoint.service.ServiceEntityCustomermst;
import net.unopoint.service.ServiceEntityIncidentmaster;

import net.unopoint.serviceimpl.ServiceImplEntityAPICreateTicketDetails;

@RestController
@Tag(name = "ControllerApiToLogTicket", description = "This API will logs tickets")
@RequestMapping(path="/createticket", produces="application/json")
@CrossOrigin(origins="*")
public class ControllerApiToLogTicket {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public static int int_checkCustomerAsset=0;
	
	@Autowired
	private RepositoryEntityIncidentmaster entityIncidentmaster;	
	
	@Autowired
	private RepositoryEntityIncidenttypemst repositoryEntityInc;
	
	@Autowired
	private RepositoryEntityCategorymst repositoryEntityCat;
	
	@Autowired 
	private ServiceEntityCustomermst serviceEntityCustomermst;
	
	@Autowired
	 private ServiceEntityIncidentmaster  serviceEntityIncidentMaster;
	
	@Autowired
	private RepositoryEntityBranchcategorymst repositoryEntityBranchCat;

	@Autowired
	private ServiceImplEntityAPICreateTicketDetails serviceImplAPICreateTicketDetails; 
	
	@GetMapping
	public ResponseEntity<BeanJsonResponse> getCustomer(@RequestParam(name="custname",required=false)String customerName,
			@RequestParam(name="panelid",required=false)String panelId,
			@RequestParam(name="type",required=false)String type,
			@RequestParam(name="title",required=false)String title,
			@RequestParam(name="category",required=false)String category,
			@RequestParam(name="branchcat",required=false)String branchCategory,
			@RequestParam(name="user",required=false)String user,
			@RequestParam(name="password",required=false)String password,
			@RequestParam(name="desc",required=false)String description) {
		
		if(panelId==null || customerName==null 
				|| type==null || title==null || category==null || branchCategory==null || user==null || password==null || description==null) {
			BeanJsonResponse b1 = new BeanJsonResponse();
			b1.setIncidentId(" ");
			b1.setResponseStatus("Please Check Your URL, Parameters are missing");
			return new ResponseEntity<BeanJsonResponse>(b1,HttpStatus.BAD_REQUEST);
		}
		
		int int_assetCount=0;
		String m_strCustId="";
		String m_strIncType="";
		String m_strCatId="";
		String m_strBranchCat="";
		BeanJsonResponse b11 = new BeanJsonResponse();   
		if(user.equals("piyush@api") && password.equals("Piyush@securepassword")) {
			try {
				EntityCustomermst customermst=serviceEntityCustomermst.findByCustomerNameAndDeleteFlag(customerName, "N");	
			    m_strCustId=String.valueOf(customermst.getTypeId());
			}catch(Exception ex) {  
				b11.setResponseStatus("Customer Not Exist"); 
				return new ResponseEntity<BeanJsonResponse>(b11, HttpStatus.BAD_REQUEST); 
		    }
		    
		
		try {
			  
					int_assetCount =getCustomerDetails(m_strCustId,panelId);
		
					if (int_assetCount == 0) {
						b11.setResponseStatus("Please Check Customer And Panel Id");
						return new ResponseEntity<BeanJsonResponse>(b11, HttpStatus.BAD_REQUEST);
					}
			
		    }catch(Exception ex) { ex.printStackTrace(); }
		
		
		try {
				EntityIncidenttypemst entityIncident=repositoryEntityInc.findBytypeValueAndDeleteFlag(type, "N");
				m_strIncType=String.valueOf(entityIncident.getTypeId());
				
				EntityCategorymst  entityCategory=repositoryEntityCat.findByTypeValueCategoryAndDeleteFlagCategory(category, "N");
			    m_strCatId=String.valueOf(entityCategory.getTypeIdCategory());
				
				EntityBranchcategorymst entityBranchCategory=repositoryEntityBranchCat.findByTypeValueBcmAndDeleteFlagBcm(branchCategory, "N");
				m_strBranchCat=String.valueOf(entityBranchCategory.getTypeIdBcm());
		}catch(Exception ex) { 
			b11.setResponseStatus("Please Check Incidnet Type Or Category or Branch Category");
			return new ResponseEntity<BeanJsonResponse>(b11, HttpStatus.BAD_REQUEST);
		}
		
		if(checkConditon(m_strCustId,panelId,title,m_strCatId)) {
		    
			BeanJsonResponse beanJsonResponse=serviceEntityIncidentMaster.createIncident(m_strCustId,panelId,title,m_strCatId,m_strIncType,m_strBranchCat,description);
			
			if(beanJsonResponse.getIncidentId()!=null && !beanJsonResponse.getIncidentId().equals("")) {
				serviceImplAPICreateTicketDetails.insertIntoApiLog(beanJsonResponse.getIncidentId(),m_strCustId,panelId,title,m_strCatId,m_strIncType,m_strBranchCat,description);
				return new ResponseEntity<BeanJsonResponse>(beanJsonResponse,HttpStatus.OK);
			}else {
				return new ResponseEntity<BeanJsonResponse>(ticketNotCreated(panelId),HttpStatus.OK);	
			}
			//return new ResponseEntity<BeanJsonResponse>(serviceEntityIncidentMaster.createIncident(m_strCustId,panelId,title,m_strCatId,m_strIncType,m_strBranchCat,description),HttpStatus.OK);
			
		} else { 
			return new ResponseEntity<BeanJsonResponse>(ticketNotCreated(panelId),HttpStatus.OK);
		   }
		}else {
			
		
			BeanJsonResponse b1 = new BeanJsonResponse();
			b1.setIncidentId(" ");
			b1.setResponseStatus("Access Denied");
			return new ResponseEntity<BeanJsonResponse>(b1,HttpStatus.FORBIDDEN);
		
		}
	}
	
	public BeanJsonResponse ticketNotCreated(String panelId) {
		BeanJsonResponse beanJsonResponse=new BeanJsonResponse();
		beanJsonResponse.setIncidentId("NOT CREATED");
		beanJsonResponse.setResponseStatus("Duplicate Call For Panel Id:- " +panelId);
		return beanJsonResponse;
	}   
	
	   public boolean checkConditon(String m_strCustId,String panelId,String title,String m_strCatId) {
		   int count=entityIncidentmaster.getTicketCount(m_strCustId, panelId, title, m_strCatId);
		   if(count==0) {
			   return true;
		   }else {
			   return false;
		   }
	   }
	
	 
	  public int getCustomerDetails(String m_strLongID,String assetId) {
			
			    String sqlSelect = "select count(*) from customerasset_cad"+m_strLongID+ " where assetId='"+assetId+"' ";
	             int count=0;
			      
	             @SuppressWarnings("unused")
		         List<BeanCustomerasset_cad> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<BeanCustomerasset_cad>() {
		        	 public BeanCustomerasset_cad mapRow(ResultSet result, int rowNum) throws SQLException {
			        	 BeanCustomerasset_cad contact = new BeanCustomerasset_cad();        	 
			        	 int_checkCustomerAsset=result.getInt(1);
			        	 return contact;  
			        	 }
		        	 });
			      count=int_checkCustomerAsset;
		         return count;
	 }

	

//	 @GetMapping
//	 public ResponseEntity<Entity_Customerasset_cad> getAsset(@RequestParam("id") String assetID) {		 
//	     return new ResponseEntity<Entity_Customerasset_cad>(repoEntity.findByAssetID(assetID), HttpStatus.OK);
//	 }

}
