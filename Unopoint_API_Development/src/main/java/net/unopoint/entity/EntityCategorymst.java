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
@Table(name="categorymst_cm ")
public class EntityCategorymst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_cm")
	private Long typeIdCategory;
	
	@Column(name="typevalue_cm")
	private String typeValueCategory;
	
	@Column(name="deleteflag_cm")
	private String deleteFlagCategory;
}
