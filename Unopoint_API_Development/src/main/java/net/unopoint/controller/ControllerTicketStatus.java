package net.unopoint.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.unopoint.bean.BeanJsonTicketResponse;
import net.unopoint.service.ServiceEntityIncidentmaster;

@Tag(name = "ControllerTicketStatus", description = "Returns Ticket Status whether ticket is open closed or paused")
@RestController
@RequestMapping(path="/gettickstatus",produces="application/json")
@CrossOrigin(origins="*")
public class ControllerTicketStatus {

    public String m_strTicketStatus="";	
	public static Long ticketIdStat=0L;
    
	
	
	@Autowired
	private ServiceEntityIncidentmaster entityIncidentmaster;
	
	@GetMapping
	public ResponseEntity<BeanJsonTicketResponse> returnTicketStatus(@RequestParam(name="ticketId",required=false)String ticketId,
			@RequestParam(name="user",required=false)String user,
			@RequestParam(name="password",required=false)String password			
			) {
		
	     	
			if(ticketId==null || ticketId.isEmpty()  || ticketId.equals("")) {
				BeanJsonTicketResponse b1=new BeanJsonTicketResponse();
				b1.setTicketStatus("Please Enter Ticket Details");
		        return new ResponseEntity<BeanJsonTicketResponse>(b1,HttpStatus.BAD_REQUEST);
			}
			
			if(!user.equals("piyush@api") || !password.equals("piyush@securepassword")) {
				BeanJsonTicketResponse b1=new BeanJsonTicketResponse();
				b1.setTicketStatus("Access Denied");
		        return new ResponseEntity<BeanJsonTicketResponse>(b1,HttpStatus.BAD_REQUEST);
			}
			
			/** CHECK TICKET EXISIT OR NOT**/
			try {
				
       			Long m_strTicketID = Long.parseLong(ticketId);
       			ticketIdStat=m_strTicketID;
       			BeanJsonTicketResponse b1=entityIncidentmaster.findTicketStatus(m_strTicketID);
       		    return new ResponseEntity<BeanJsonTicketResponse>(b1,HttpStatus.OK);
			
			}catch(Exception ex) {
				BeanJsonTicketResponse b1=new BeanJsonTicketResponse();
				b1.setTicketStatus("Please Check Ticket "+ticketIdStat);
		        return new ResponseEntity<BeanJsonTicketResponse>(b1,HttpStatus.BAD_REQUEST);
			}
		
	}
	
}
