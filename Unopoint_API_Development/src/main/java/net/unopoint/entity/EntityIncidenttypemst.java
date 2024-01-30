package net.unopoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="incidenttypemst_itm")
public class EntityIncidenttypemst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_itm")
	private Long typeId;
	
	@Column(name="typevalue_itm")
	private String typeValue;
	
	@Column(name="deleteflag_itm")
	private String deleteFlag;
	
}
