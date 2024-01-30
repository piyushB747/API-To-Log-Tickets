package net.unopoint.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.unopoint.entity.Entity_Customerasset_cad;
import net.unopoint.repository.RepoEntity_Customerasset_cad;
import net.unopoint.service.Service_Entity_Customerasset_cad;
@Service
public class ServiceImpl_Entity_Customerasset_cad implements Service_Entity_Customerasset_cad{

	@Autowired
	private RepoEntity_Customerasset_cad repoEntityCustAsset;
	
	@Override
	public List<Entity_Customerasset_cad> getAllAssets() {
		return repoEntityCustAsset.findAll();
	}

}
