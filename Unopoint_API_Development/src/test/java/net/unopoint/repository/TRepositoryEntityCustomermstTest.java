package net.unopoint.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.unopoint.entity.EntityCustomermst;
@SpringBootTest
class TRepositoryEntityCustomermstTest {

	@Autowired
	private RepositoryEntityCustomermst repositoryEntityCustomermst;
	
	@Test
	void testToFetchCustomerFromName() {
		try {
		EntityCustomermst e1=repositoryEntityCustomermst.findByCustomerNameAndDeleteFlag("PNB Bank", "N");
		}catch(Exception ex) {  ex.printStackTrace(); }
		fail("Not yet implemented");
		
	}

}
