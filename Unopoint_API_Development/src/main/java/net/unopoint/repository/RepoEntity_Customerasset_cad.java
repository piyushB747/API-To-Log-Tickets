package net.unopoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.unopoint.entity.Entity_Customerasset_cad;

public interface RepoEntity_Customerasset_cad extends JpaRepository<Entity_Customerasset_cad, Long>{

	
    //public Product findByName(String name);
	
    public abstract Entity_Customerasset_cad findByAssetID(String AssetID);
}
