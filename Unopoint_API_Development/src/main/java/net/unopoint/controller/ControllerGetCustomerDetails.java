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
import net.unopoint.entity.EntityCustomermst;
import net.unopoint.service.ServiceEntityCustomermst;
@Tag(name = "ControllerGetCustomerDetails", description = "Returns Customer Details")
@RestController
@RequestMapping(path="/getcust", produces = "application/json")
@CrossOrigin(origins = "*")
public class ControllerGetCustomerDetails {
	int int_checkCustomerAsset =0;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ServiceEntityCustomermst serviceEntityCustomermst;

	@GetMapping
	public ResponseEntity<BeanCustomerasset_cad> getCustomer(
			@RequestParam(name="custname",required=false)String customerName,
			@RequestParam(name="panelid",required=false)String panelId,
			@RequestParam(name="user",required=false)String user,
			@RequestParam(name="password",required=false)String password
			){
		
		
		BeanCustomerasset_cad beanJsonResponse = new BeanCustomerasset_cad();
		if(panelId==null || customerName==null || panelId.equals("") || customerName.equals("")) {
			beanJsonResponse.setMessage("Access Denied");
			return new ResponseEntity<BeanCustomerasset_cad>(beanJsonResponse, HttpStatus.BAD_REQUEST);
			
		}
		
		if(!user.equals("piyush@api") || !password.equals("piyush@securepassword")) {
			beanJsonResponse.setMessage("Access Denied");
			return new ResponseEntity<BeanCustomerasset_cad>(beanJsonResponse, HttpStatus.BAD_REQUEST);
		}
		
		int int_assetCount =0;
		String m_strCustId="";
		
		
		try {
		EntityCustomermst customermst=serviceEntityCustomermst.findByCustomerNameAndDeleteFlag(customerName, "N");
		
		m_strCustId=String.valueOf(customermst.getTypeId());
		}catch(Exception ex) { 
			ex.printStackTrace();
			beanJsonResponse.setPoplocation("Please Check Customer And Panel Id or customer not exist");
			return new ResponseEntity<BeanCustomerasset_cad>(beanJsonResponse, HttpStatus.BAD_REQUEST);
		}
		try{
		
				int_assetCount =getCustomerCount(m_strCustId,panelId);
				
				if (int_assetCount == 0) {
					beanJsonResponse.setMessage("Please Check Customer And Panel Id");
					return new ResponseEntity<BeanCustomerasset_cad>(beanJsonResponse, HttpStatus.BAD_REQUEST);
				}
			
		} catch(Exception e) {
			beanJsonResponse.setMessage("Please Check Customer And Panel Id");
			return new ResponseEntity<BeanCustomerasset_cad>(beanJsonResponse, HttpStatus.BAD_REQUEST);
		}
		try {
		      BeanCustomerasset_cad l1=getCustomerDetails(m_strCustId,panelId);
		      return new ResponseEntity<BeanCustomerasset_cad>(l1, HttpStatus.OK);
		}catch(Exception ex) { ex.printStackTrace(); }
		
		
		beanJsonResponse.setPoplocation("Please Check Customer And Panel Id");
		return new ResponseEntity<BeanCustomerasset_cad>(beanJsonResponse, HttpStatus.BAD_REQUEST);
	}
	
//	public List<BeanCustomerasset_cad> getCustomerDetails(String m_strLongID,String assetId) {
//		
//	    String sqlSelect = "select * from customerasset_cad"+m_strLongID+ " where assetId='"+assetId+"' ";
//	      
//	    BeanCustomerasset_cad listContact = (BeanCustomerasset_cad) jdbcTemplate.query(sqlSelect, new RowMapper<BeanCustomerasset_cad>() {
//        	 public BeanCustomerasset_cad mapRow(ResultSet result, int rowNum) throws SQLException {
//	        	 BeanCustomerasset_cad contact = new BeanCustomerasset_cad();        	 
//	        	 contact.setEcode(result.getString("ecode"));
//	        	 contact.setCustomerAddress(result.getString("CustomerAddress"));
//	        	 contact.setRegion(result.getString("region_cad"));
//	        	 contact.setState(result.getString("State"));
//	        	 contact.setPoplocation(result.getString("POPLocation"));
//	        	 contact.setCity(result.getString("City"));
//	        	 
//	        	 return contact;  
//	        	 }
//        	 });
//	      
//         return listContact;
//	}
	
	public BeanCustomerasset_cad getCustomerDetails(String m_strLongID,String assetId) {
		
	    String sqlSelect = "select * from customerasset_cad"+m_strLongID+ " where assetId='"+assetId+"' ";
        
	      BeanCustomerasset_cad beanCustomerasset_cad=new BeanCustomerasset_cad();
         
	      
         List<BeanCustomerasset_cad> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<BeanCustomerasset_cad>() {
        	 public BeanCustomerasset_cad mapRow(ResultSet result, int rowNum) throws SQLException {
	        	 BeanCustomerasset_cad contact = new BeanCustomerasset_cad();        	 
	        	 
 	        	 contact.setEcode(result.getString("ecode"));
	        	 contact.setCustomerAddress(result.getString("CustomerAddress"));
	        	 contact.setRegion(result.getString("region_cad"));
	        	 contact.setState(result.getString("State"));
	        	 contact.setPoplocation(result.getString("POPLocation"));
	        	 contact.setCity(result.getString("City"));
	        	 return contact;  
	        	 }
        	 });
         
         listContact.forEach((p)->{
            beanCustomerasset_cad.setCity(p.getCity());
            beanCustomerasset_cad.setCustomerAddress(p.getCustomerAddress());
            beanCustomerasset_cad.setRegion(p.getRegion());
            beanCustomerasset_cad.setState(p.getState());
            beanCustomerasset_cad.setPoplocation(p.getPoplocation());
            beanCustomerasset_cad.setEcode(p.getEcode());
            beanCustomerasset_cad.setMessage("Details of the customer");
         });
         
	    
         return beanCustomerasset_cad;
}
	
	
	
	public int getCustomerCount(String m_strLongID,String assetId) {

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
	
}
