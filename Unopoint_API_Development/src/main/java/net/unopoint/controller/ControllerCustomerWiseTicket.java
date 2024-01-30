package net.unopoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.unopoint.dto.DtoCount;
import net.unopoint.dto.DtoErrors;
import net.unopoint.dto.DtoIncidentMaster;
import net.unopoint.entity.EntityCustomermst;
import net.unopoint.repository.RepositoryEntityIncidentmaster;
import net.unopoint.service.ServiceEntityCustomermst;
import net.unopoint.service.ServiceEntityIncidentmaster;

@RestController
@Tag(name = "ControllerCustomerWiseTicket", description = "Returns Tickets CustomerWise")
@RequestMapping("/ticketcustomerwise")
@CrossOrigin(origins="*")
public class ControllerCustomerWiseTicket {


	
	@Autowired 
	private ServiceEntityCustomermst serviceEntityCustomermst;
	
	@Autowired
	private ServiceEntityIncidentmaster serviceEntityIncidentmaster;
	
	
	
	@Autowired
	private RepositoryEntityIncidentmaster repoEntityIncidentMaster;
	
	@GetMapping("/counts")
	public ResponseEntity<?> getOpenTicketAndTotalTicketsCount(
			@RequestParam(name="user",required=false)String user,
			@RequestParam(name="password",required=false)String password,
			@RequestParam(name="customername",required=false)String customerNameRequest){
		
		DtoErrors dtoErrors=new DtoErrors();	
		if(		user==null || password==null || user.equals("") || password.equals("")) 
	    {
			 dtoErrors.setMessage("Parameters Is Missing");
			 dtoErrors.setMessageDescription("custName or panelid parmaters are missing");
			 dtoErrors.setHttpStatus("Http Status 404");
			 return new ResponseEntity<DtoErrors>(dtoErrors,HttpStatus.BAD_REQUEST);
		
	    }
	    

		if (user.equals("piyush@api") && password.equals("piyush@securepassword")) {

			if(customerNameRequest!=null  && customerNameRequest.equals("")==false) {
			
				EntityCustomermst entityCustomermst=serviceEntityCustomermst.findByCustomerNameAndDeleteFlag(customerNameRequest, "N");	
				if(entityCustomermst != null) {		    
					long nL_strCustId = entityCustomermst.getTypeId();
					int n_custId = (nL_strCustId >= Integer.MIN_VALUE && nL_strCustId <= Integer.MAX_VALUE) ? (int) nL_strCustId : 0;
                    int totalOpenCount=repoEntityIncidentMaster.getCountOfOpenTicketCustomerWise(n_custId);
                    int totalTicketCount=repoEntityIncidentMaster.getCountoFTotalTicketCustomerWise(n_custId);
                    DtoCount dCounts=new DtoCount();
                    dCounts.setOpen(totalOpenCount);
                    dCounts.setTotalTicket(totalTicketCount);
                    return ResponseEntity.status(HttpStatus.OK)
    		                .header("custom-header", "unopoint")
    		                .contentType(MediaType.APPLICATION_JSON)             
    		                .body(dCounts);
				    
					
			        }else {
			        	dtoErrors.setMessage("Customer Not Found");
			        	return ResponseEntity.status(HttpStatus.OK)
	    		                .header("custom-header", "unopoint")
	    		                .contentType(MediaType.APPLICATION_JSON)             
	    		                .body(dtoErrors);
			        	
			        }
				
			
			}else {
				   dtoErrors.setMessage("Customer Name parameter is empty");
				   dtoErrors.setMessageDescription("Customer Name parameter is empty");
				//   dtoErrors.setHttpStatus("Http Status 404");
				
			}
			
		}else {
			   dtoErrors.setMessage("Access Denied");
			   dtoErrors.setMessageDescription("Please check Your user and password");
			 //  dtoErrors.setHttpStatus("Http Status 404");
		 return new ResponseEntity<DtoErrors>(dtoErrors,HttpStatus.FORBIDDEN);
	
		}
	
		   dtoErrors.setMessage("Access Denied");
		   dtoErrors.setMessageDescription("Please check Your user and password");
		 //dtoErrors.setHttpStatus("Http Status 404");
	 return new ResponseEntity<DtoErrors>(dtoErrors,HttpStatus.FORBIDDEN);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAllTicketCustomerWise(
			@RequestParam(name="user",required=false)String userRequest,
			@RequestParam(name="password",required=false)String passwordRequest,
			@RequestParam(name="customername",required=false)String customerNameRequest,
			@RequestParam(name="panelid",required=false)String panelIdRequest){
		
		DtoErrors dtoErrors=new DtoErrors();	
		String m_strCustId="";
		
		/*** Check Parameters are Missing or Not ***/
	    if(userRequest==null || passwordRequest==null || userRequest.equals("") || passwordRequest.equals("")) 
	    {
			 dtoErrors.setMessage("Parameters Is Missing");
			 dtoErrors.setMessageDescription("custName or panelid parmaters are missing");
			 dtoErrors.setHttpStatus("Http Status 404");
			 return new ResponseEntity<DtoErrors>(dtoErrors,HttpStatus.BAD_REQUEST);
		}
	    
	    /**Check userRequest and passwordRequest Is correct Or Not **/
		if (userRequest.equals("Securens@api") && passwordRequest.equals("Securens@securepassword")) {
			
			if(customerNameRequest!=null  && customerNameRequest.equals("")==false) {
				
				EntityCustomermst entityCustomermst=serviceEntityCustomermst.findByCustomerNameAndDeleteFlag(customerNameRequest, "N");	
				if(entityCustomermst != null) {		    
					m_strCustId = String.valueOf(entityCustomermst.getTypeId());
				    List<DtoIncidentMaster> l1=serviceEntityIncidentmaster.returnListOfAllTicketCustomerWiseLogged(m_strCustId);
					return new ResponseEntity<List<DtoIncidentMaster>>(l1,HttpStatus.OK);
					
				} else {
				  m_strCustId="";
					 dtoErrors.setMessage("Customer Not Exist");
					 dtoErrors.setMessageDescription("Parameters are missing");
					 dtoErrors.setHttpStatus("Http Status 404");
					
					 return ResponseEntity.status(HttpStatus.OK)
			                .header("custom-header", "unopoint")
			                .contentType(MediaType.APPLICATION_JSON)             
			                .body(dtoErrors);

				}

				
			}else {
				 dtoErrors.setMessage("Parameters are Missing");
				 dtoErrors.setMessageDescription("Parameters are missing");
				 dtoErrors.setHttpStatus("Http Status 404");
				
				 return ResponseEntity.status(HttpStatus.OK)
		                .header("custom-header", "unopoint")
		                .contentType(MediaType.APPLICATION_JSON)             
		                .body(dtoErrors);
				
			}
			
			
			
			
		}else {
			 dtoErrors.setMessage("Access Denied");
			 dtoErrors.setMessageDescription("Please check Your userRequest and passwordRequest");
			 dtoErrors.setHttpStatus("Http Status 404");
		 return new ResponseEntity<DtoErrors>(dtoErrors,HttpStatus.FORBIDDEN);
	
		}
		
	}
}
