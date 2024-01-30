package net.unopoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="branchcategorymst_bcm")
public class EntityBranchcategorymst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_bcm")
	private Long typeIdBcm;
	
	@Column(name="typevalue_bcm")
	private String typeValueBcm;
	
	@Column(name="deleteflag_bcm")
	private String deleteFlagBcm;
}
