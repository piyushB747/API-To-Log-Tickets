package net.unopoint.service;

import net.unopoint.entity.EntityCustomermst;


public interface ServiceEntityCustomermst {
	
	public abstract EntityCustomermst findByCustomerNameAndDeleteFlag(String customerName,String deleteflag);
}
