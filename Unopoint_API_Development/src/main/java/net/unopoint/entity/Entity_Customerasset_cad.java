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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="customerasset_cad")
public class Entity_Customerasset_cad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cad")
	private Long id_cad; 

	@Column(name="AssetID")
	private String assetID;
	
	@Column(name="CustomerAddress")
	private String CustomerAddress;
	
	@Column(name="City")
	private String City;
	
	@Column(name="State")
	private String State;

	public Entity_Customerasset_cad(String assetID, String customerAddress, String city, String state) {
		super();
		this.assetID = assetID;
		CustomerAddress = customerAddress;
		City = city;
		State = state;
	}

	
	
}
