package net.unopoint.repository;



import java.util.List;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.unopoint.dto.DtoIncidentMaster;
import net.unopoint.entity.EntityCustomermst;
import net.unopoint.entity.EntityIncidentmaster;
import net.unopoint.service.ServiceEntityIncidentmaster;
@SpringBootTest
class RepositoryEntityIncidentmasterTest {

	@Autowired
	private RepositoryEntityCustomermst serviceEntityIncidentmaster;
	
	@Autowired
	private RepositoryEntityCustomermst repoEntityCust;
	
	@Autowired(required = true)
	private ModelMapper modelMapper;

	
	@Autowired
	private RepositoryEntityIncidentmaster repoIncidentMaster;
	
	
	@Test
	public void returnListOfCount() {
		int count=repoIncidentMaster.getCountOfOpenTicket();
		System.out.println(count);
	}
	
	@Test
	public void testToFetchTicketCustomerWise() {
		
		try {
//		EntityCustomermst customermst=repoEntityCust.findByCustomerNameAndDeleteFlag("ICICIwe Bank", "N");	
//	    String m_strCustId=String.valueOf(customermst.getTypeId());

			String customermst=repoEntityCust.findCustomerNameById(10L);
			
			System.out.println(customermst+"CustomerName");}catch(Exception ex) { ex.printStackTrace(); }
		try {
//		List<DtoIncidentMaster>  l2= repoIncidentMaster.
//				returnListOfTicketCreatedByMobileCustomerWise("A01306",10)
//				.stream().map(this::convertEntityToDto)
//				.collect(Collectors.toList());
//		
//		l2.forEach((p)->{
//			System.out.println(p.getTypeIdIm()+ " ANUSHKA "+p.getStatusSmIm());
//		});
		
		}catch(Exception ex) { ex.printStackTrace(); }	
		
	}
	
	@Test
	public void testToFetchTicket() {
//		
//		return this.userRepository.findAll()
//				.stream().map(this::convertEntityToDto)
//				.collect(Collectors.toList());
		
//		List<EntityIncidentmaster> l1=repoIncidentMaster.returnListOfTicketCreatedByMobile();
	    
		 
		List<DtoIncidentMaster>  l2= repoIncidentMaster.
				returnListOfTicketCreatedByMobile()
				.stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
//		
//		return this.userRepository.findAll()
//				.stream().map(this::convertEntityToDto)
//				.collect(Collectors.toList());
//			
//        	System.out.println(p.getTypeIdIm()+  " Before "+p.getStatusSmIm());
//		});
//		l1.forEach((p)->{
//			if(p.getStatusSmIm()==7) {
//        		p.setStatusSmIm(1);
//        	}
//		});
//		
//		System.out.println("PIYUSH SINGH");
//		
		l2.forEach((p)->{
        	 if(p.getStatusSmIm().equals("1") || p.getStatusSmIm().equals("7")) {
        		 p.setStatusSmIm("Open");
        	 }else if(p.getStatusSmIm().equals("3")) {
        		 p.setStatusSmIm("Paused");
        	 }else if(p.getStatusSmIm().equals("5")) {
        		 p.setStatusSmIm("Technician Closed");
        	 }else {
        		 p.setStatusSmIm("ReOpened");
        	 }
		});
		
		l2.forEach((p)->{
			System.out.println(p.getTypeIdIm()+ " PIYUSH "+p.getStatusSmIm());
		});
	}


		public DtoIncidentMaster convertEntityToDto(EntityIncidentmaster user) {		
		    DtoIncidentMaster userLocationDto=new DtoIncidentMaster();
			userLocationDto=modelMapper.map(user, DtoIncidentMaster.class);
			return userLocationDto;		
		}
}
