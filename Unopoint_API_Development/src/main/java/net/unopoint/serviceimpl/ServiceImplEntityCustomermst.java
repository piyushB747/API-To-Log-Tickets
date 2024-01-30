package net.unopoint.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.unopoint.entity.EntityCustomermst;
import net.unopoint.repository.RepositoryEntityCustomermst;
import net.unopoint.service.ServiceEntityCustomermst;
@Service
public class ServiceImplEntityCustomermst implements ServiceEntityCustomermst{

	@Autowired
	private RepositoryEntityCustomermst repoEntityCust;
	
	@Override
	public EntityCustomermst findByCustomerNameAndDeleteFlag(String customerName,String deleteflag) {
		EntityCustomermst e1=repoEntityCust.findByCustomerNameAndDeleteFlag(customerName, deleteflag);
		return e1;
	}

}
