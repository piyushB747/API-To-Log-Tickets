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
@Table(name="statusmst_sm")
public class EntityStatusmstsm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_sm")
	private Long typeidSm;
	
	@Column(name="typevalue_sm")
	private String typeValueSm;
	
	@Column(name="deleteflag_sm")
	private String deleteFlagSm;

	public EntityStatusmstsm(String typeValueSm, String deleteFlagSm) {
		super();
		this.typeValueSm = typeValueSm;
		this.deleteFlagSm = deleteFlagSm;
	}
	
	
	
}
