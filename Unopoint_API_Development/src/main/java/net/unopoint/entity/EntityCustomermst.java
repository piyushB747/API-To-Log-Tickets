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
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customermst_cm")
public class EntityCustomermst {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_cm")
	private Long typeId;
    
	@Column(name="customername_cm")
	private String customerName;

	@Column(name="deleteflag_cm")
	private String deleteFlag;

	public EntityCustomermst(String customerName, String deleteFlag) {
		super();
		this.customerName = customerName;
		this.deleteFlag = deleteFlag;
	}
	
}
